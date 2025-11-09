#!/bin/bash

# AWS Full-Stack Deployment Script
# Make sure to set these variables before running

# Configuration - REPLACE THESE VALUES
export AWS_ACCOUNT_ID="YOUR_ACCOUNT_ID"
export AWS_REGION="us-east-1"
export BUCKET_NAME="YOUR_UNIQUE_BUCKET_NAME"
export ECR_REPOSITORY="simple-backend"
export ECS_CLUSTER="simple-backend-cluster"
export ECS_SERVICE="simple-backend-service"

echo "🚀 Starting AWS Full-Stack Deployment..."

# Check if AWS CLI is configured
if ! aws sts get-caller-identity >/dev/null 2>&1; then
    echo "❌ AWS CLI not configured. Please run 'aws configure' first."
    exit 1
fi

echo "✅ AWS CLI is configured"

# Get AWS Account ID automatically if not set
if [ "$AWS_ACCOUNT_ID" = "YOUR_ACCOUNT_ID" ]; then
    AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
    echo "🔍 Auto-detected AWS Account ID: $AWS_ACCOUNT_ID"
fi

echo "📦 Step 1: Creating ECR Repository..."
aws ecr create-repository --repository-name $ECR_REPOSITORY --region $AWS_REGION 2>/dev/null || echo "Repository might already exist"

echo "🔐 Step 2: Getting ECR login token..."
aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com

echo "🏗️ Step 3: Building Docker image..."
cd simpleBackendApp
docker build -t $ECR_REPOSITORY .

echo "🏷️ Step 4: Tagging image..."
docker tag $ECR_REPOSITORY:latest $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPOSITORY:latest

echo "📤 Step 5: Pushing to ECR..."
docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_REGION.amazonaws.com/$ECR_REPOSITORY:latest

echo "🏭 Step 6: Creating ECS Cluster..."
aws ecs create-cluster --cluster-name $ECS_CLUSTER --capacity-providers FARGATE 2>/dev/null || echo "Cluster might already exist"

echo "📝 Step 7: Creating CloudWatch Log Group..."
aws logs create-log-group --log-group-name /ecs/$ECR_REPOSITORY --region $AWS_REGION 2>/dev/null || echo "Log group might already exist"

echo "⚙️ Step 8: Updating task definition..."
# Update task definition with actual values
sed -i.bak "s/YOUR_ACCOUNT_ID/$AWS_ACCOUNT_ID/g" task-definition.json
sed -i.bak "s/YOUR_REGION/$AWS_REGION/g" task-definition.json

echo "📋 Step 9: Registering task definition..."
aws ecs register-task-definition --cli-input-json file://task-definition.json

echo "🛡️ Step 10: Creating security group..."
SECURITY_GROUP_ID=$(aws ec2 create-security-group --group-name simple-backend-sg-$(date +%s) --description "Security group for simple backend" --query 'GroupId' --output text 2>/dev/null || echo "")

if [ ! -z "$SECURITY_GROUP_ID" ]; then
    aws ec2 authorize-security-group-ingress --group-id $SECURITY_GROUP_ID --protocol tcp --port 8080 --cidr 0.0.0.0/0
    echo "✅ Created security group: $SECURITY_GROUP_ID"
else
    echo "⚠️ Please create security group manually and update service-definition.json"
fi

echo "🪣 Step 11: Creating S3 bucket..."
aws s3 mb s3://$BUCKET_NAME --region $AWS_REGION 2>/dev/null || echo "Bucket might already exist"

echo "🌐 Step 12: Configuring S3 for static website hosting..."
aws s3 website s3://$BUCKET_NAME --index-document index.html --error-document index.html

echo "📝 Step 13: Setting bucket policy..."
# Update bucket policy
cd ..
sed "s/YOUR_BUCKET_NAME/$BUCKET_NAME/g" bucket-policy.json > temp-bucket-policy.json
aws s3api put-bucket-policy --bucket $BUCKET_NAME --policy file://temp-bucket-policy.json
rm temp-bucket-policy.json

echo "📤 Step 14: Uploading frontend files..."
cd simpleFrontendApp
aws s3 sync . s3://$BUCKET_NAME --delete

echo "🎉 Deployment complete!"
echo ""
echo "📍 URLs:"
echo "🔗 Frontend: http://$BUCKET_NAME.s3-website-$AWS_REGION.amazonaws.com"
echo ""
echo "⚠️ MANUAL STEPS REQUIRED:"
echo "1. Update service-definition.json with your subnet IDs and security group ID"
echo "2. Create ECS service: aws ecs create-service --cli-input-json file://service-definition.json"
echo "3. Get ECS service public IP and update simpleFrontendApp/script.js"
echo "4. Re-upload frontend files after updating script.js"
echo ""
echo "📖 See DEPLOYMENT_GUIDE.md for detailed instructions"
