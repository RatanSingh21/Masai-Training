#!/bin/bash

# AWS Resource Cleanup Script
# This script will clean up all AWS resources created during deployment

# Configuration - REPLACE THESE VALUES
export AWS_ACCOUNT_ID="YOUR_ACCOUNT_ID"
export AWS_REGION="us-east-1"
export BUCKET_NAME="YOUR_UNIQUE_BUCKET_NAME"
export ECR_REPOSITORY="simple-backend"
export ECS_CLUSTER="simple-backend-cluster"
export ECS_SERVICE="simple-backend-service"

echo "🧹 Starting AWS Resource Cleanup..."

# Get AWS Account ID automatically if not set
if [ "$AWS_ACCOUNT_ID" = "YOUR_ACCOUNT_ID" ]; then
    AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
    echo "🔍 Auto-detected AWS Account ID: $AWS_ACCOUNT_ID"
fi

echo "⏹️ Step 1: Stopping ECS Service..."
aws ecs update-service --cluster $ECS_CLUSTER --service $ECS_SERVICE --desired-count 0 2>/dev/null || echo "Service might not exist"

echo "⏳ Waiting for tasks to stop..."
sleep 30

echo "🗑️ Step 2: Deleting ECS Service..."
aws ecs delete-service --cluster $ECS_CLUSTER --service $ECS_SERVICE --force 2>/dev/null || echo "Service might not exist"

echo "🗑️ Step 3: Deleting ECS Cluster..."
aws ecs delete-cluster --cluster $ECS_CLUSTER 2>/dev/null || echo "Cluster might not exist"

echo "🗑️ Step 4: Deleting ECR Repository..."
aws ecr delete-repository --repository-name $ECR_REPOSITORY --force 2>/dev/null || echo "Repository might not exist"

echo "🗑️ Step 5: Deleting CloudWatch Log Group..."
aws logs delete-log-group --log-group-name /ecs/$ECR_REPOSITORY 2>/dev/null || echo "Log group might not exist"

echo "🪣 Step 6: Emptying S3 Bucket..."
aws s3 rm s3://$BUCKET_NAME --recursive 2>/dev/null || echo "Bucket might not exist"

echo "🗑️ Step 7: Deleting S3 Bucket..."
aws s3 rb s3://$BUCKET_NAME 2>/dev/null || echo "Bucket might not exist"

echo "🛡️ Step 8: Cleaning up Security Groups..."
# List security groups with our naming pattern and delete them
aws ec2 describe-security-groups --filters "Name=group-name,Values=simple-backend-sg-*" --query 'SecurityGroups[].GroupId' --output text | while read sg_id; do
    if [ ! -z "$sg_id" ]; then
        aws ec2 delete-security-group --group-id $sg_id 2>/dev/null && echo "Deleted security group: $sg_id"
    fi
done

echo "🧹 Step 9: Cleaning up Task Definitions..."
# Note: Task definitions cannot be deleted, they are just deregistered
TASK_ARNS=$(aws ecs list-task-definitions --family-prefix simple-backend-task --status ACTIVE --query 'taskDefinitionArns' --output text)
for arn in $TASK_ARNS; do
    aws ecs deregister-task-definition --task-definition $arn 2>/dev/null && echo "Deregistered: $arn"
done

echo "🎉 Cleanup complete!"
echo ""
echo "📝 Note: Some resources like task definitions cannot be completely deleted"
echo "but have been deregistered and won't incur charges."
echo ""
echo "💰 All billable resources have been cleaned up!"
