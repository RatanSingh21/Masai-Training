# Full-Stack AWS Deployment Guide

This guide will help you deploy a full-stack application using AWS ECS for the backend and AWS S3 for the frontend.

## 📋 Prerequisites

1. AWS CLI installed and configured
2. Docker installed
3. Node.js installed
4. AWS account with appropriate permissions

## 🚀 Part 1 – Backend Deployment (ECS)

### Step 1: Test Backend Locally

```bash
cd simpleBackendApp
npm install
node server.js
```

The server should start on port 8080.

### Step 2: Create ECR Repository

```bash
# Replace YOUR_REGION with your AWS region (e.g., us-east-1)
aws ecr create-repository --repository-name simple-backend --region YOUR_REGION
```

### Step 3: Get ECR Login Token

```bash
aws ecr get-login-password --region YOUR_REGION | docker login --username AWS --password-stdin YOUR_ACCOUNT_ID.dkr.ecr.YOUR_REGION.amazonaws.com
```

### Step 4: Build and Push Docker Image

```bash
# Build the image
docker build -t simple-backend .

# Tag the image
docker tag simple-backend:latest YOUR_ACCOUNT_ID.dkr.ecr.YOUR_REGION.amazonaws.com/simple-backend:latest

# Push to ECR
docker push YOUR_ACCOUNT_ID.dkr.ecr.YOUR_REGION.amazonaws.com/simple-backend:latest
```

### Step 5: Create ECS Cluster

```bash
aws ecs create-cluster --cluster-name simple-backend-cluster --capacity-providers FARGATE
```

### Step 6: Create CloudWatch Log Group

```bash
aws logs create-log-group --log-group-name /ecs/simple-backend --region YOUR_REGION
```

### Step 7: Update Task Definition

1. Replace `YOUR_ACCOUNT_ID` and `YOUR_REGION` in `task-definition.json`
2. Create/update IAM roles if needed

### Step 8: Register Task Definition

```bash
aws ecs register-task-definition --cli-input-json file://task-definition.json
```

### Step 9: Create Security Group

```bash
# Create security group
aws ec2 create-security-group --group-name simple-backend-sg --description "Security group for simple backend"

# Add inbound rule for port 8080
aws ec2 authorize-security-group-ingress --group-name simple-backend-sg --protocol tcp --port 8080 --cidr 0.0.0.0/0
```

### Step 10: Create and Run Service

Update `service-definition.json` with your subnet and security group IDs, then:

```bash
aws ecs create-service --cli-input-json file://service-definition.json
```

## 🌐 Part 2 – Frontend Deployment (S3)

### Step 1: Create S3 Bucket

```bash
# Replace YOUR_BUCKET_NAME with a unique bucket name
aws s3 mb s3://YOUR_BUCKET_NAME --region YOUR_REGION
```

### Step 2: Configure S3 for Static Website Hosting

```bash
aws s3 website s3://YOUR_BUCKET_NAME --index-document index.html --error-document index.html
```

### Step 3: Update Frontend API URL

Update the `API_BASE` variable in `simpleFrontendApp/script.js` with your ECS service public URL.

### Step 4: Set Bucket Policy for Public Access

Create a bucket policy to make the files publicly readable:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "PublicReadGetObject",
      "Effect": "Allow",
      "Principal": "*",
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::YOUR_BUCKET_NAME/*"
    }
  ]
}
```

Apply the policy:
```bash
aws s3api put-bucket-policy --bucket YOUR_BUCKET_NAME --policy file://bucket-policy.json
```

### Step 5: Upload Frontend Files

```bash
cd simpleFrontendApp
aws s3 sync . s3://YOUR_BUCKET_NAME --delete
```

## 🔧 Configuration Variables to Replace

1. `YOUR_ACCOUNT_ID` - Your AWS Account ID
2. `YOUR_REGION` - Your AWS region (e.g., us-east-1)
3. `YOUR_BUCKET_NAME` - Your S3 bucket name (must be globally unique)
4. Subnet IDs in `service-definition.json`
5. Security Group IDs in `service-definition.json`

## 📍 Getting URLs

### Backend URL
After ECS service is running, get the public IP:
```bash
aws ecs describe-tasks --cluster simple-backend-cluster --tasks $(aws ecs list-tasks --cluster simple-backend-cluster --service simple-backend-service --query 'taskArns[0]' --output text) --query 'tasks[0].attachments[0].details[?name==`networkInterfaceId`].value' --output text | xargs -I {} aws ec2 describe-network-interfaces --network-interface-ids {} --query 'NetworkInterfaces[0].Association.PublicIp' --output text
```

### Frontend URL
```
http://YOUR_BUCKET_NAME.s3-website-YOUR_REGION.amazonaws.com
```

## 🧪 Testing

1. Visit your backend URL + `/test` to verify it returns "Welcome to AWS ECR-ECS Backend!"
2. Visit your frontend URL to test the full application flow
3. Try signup and login functionality

## 🔍 Troubleshooting

1. Check ECS service logs in CloudWatch
2. Verify security group allows traffic on port 8080
3. Ensure task definition has correct image URI
4. Check frontend console for CORS errors
5. Verify S3 bucket policy allows public read access

## 🧹 Cleanup (When Done Testing)

```bash
# Delete ECS service
aws ecs delete-service --cluster simple-backend-cluster --service simple-backend-service --force

# Delete ECS cluster
aws ecs delete-cluster --cluster simple-backend-cluster

# Delete ECR repository
aws ecr delete-repository --repository-name simple-backend --force

# Empty and delete S3 bucket
aws s3 rm s3://YOUR_BUCKET_NAME --recursive
aws s3 rb s3://YOUR_BUCKET_NAME
```

## 🎯 Expected Deliverables

- **Backend URL**: `http://YOUR_ECS_PUBLIC_IP:8080`
- **Frontend URL**: `http://YOUR_BUCKET_NAME.s3-website-YOUR_REGION.amazonaws.com`
