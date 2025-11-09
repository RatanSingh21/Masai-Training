# Quick Command Reference

## 🚀 Deployment Commands

### 1. Automated Deployment (Recommended)
```bash
# Edit the variables in deploy.sh first, then run:
./deploy.sh
```

### 2. Manual Step-by-Step

#### Backend (ECS)
```bash
# 1. Create ECR repository
aws ecr create-repository --repository-name simple-backend --region us-east-1

# 2. Login to ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com

# 3. Build and push Docker image
cd simpleBackendApp
docker build -t simple-backend .
docker tag simple-backend:latest YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/simple-backend:latest
docker push YOUR_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/simple-backend:latest

# 4. Create ECS cluster
aws ecs create-cluster --cluster-name simple-backend-cluster --capacity-providers FARGATE

# 5. Create CloudWatch log group
aws logs create-log-group --log-group-name /ecs/simple-backend

# 6. Register task definition (update task-definition.json first)
aws ecs register-task-definition --cli-input-json file://task-definition.json

# 7. Create service (update service-definition.json first)
aws ecs create-service --cli-input-json file://service-definition.json
```

#### Frontend (S3)
```bash
# 1. Create S3 bucket
aws s3 mb s3://your-bucket-name

# 2. Enable static website hosting
aws s3 website s3://your-bucket-name --index-document index.html

# 3. Set bucket policy
aws s3api put-bucket-policy --bucket your-bucket-name --policy file://bucket-policy.json

# 4. Upload frontend files
cd simpleFrontendApp
aws s3 sync . s3://your-bucket-name --delete
```

## 🔧 Utility Commands

### Update Frontend API URL
```bash
# After getting ECS public IP
./update-frontend-url.sh YOUR_ECS_PUBLIC_IP
# Or with S3 bucket name to auto-upload
./update-frontend-url.sh YOUR_ECS_PUBLIC_IP your-bucket-name
```

### Get ECS Public IP
```bash
# Get task ARN
TASK_ARN=$(aws ecs list-tasks --cluster simple-backend-cluster --service simple-backend-service --query 'taskArns[0]' --output text)

# Get network interface ID
ENI_ID=$(aws ecs describe-tasks --cluster simple-backend-cluster --tasks $TASK_ARN --query 'tasks[0].attachments[0].details[?name==`networkInterfaceId`].value' --output text)

# Get public IP
aws ec2 describe-network-interfaces --network-interface-ids $ENI_ID --query 'NetworkInterfaces[0].Association.PublicIp' --output text
```

### Test Backend
```bash
# Test locally
curl http://localhost:8080/test

# Test on ECS
curl http://YOUR_ECS_PUBLIC_IP:8080/test
```

### Monitor ECS Service
```bash
# Check service status
aws ecs describe-services --cluster simple-backend-cluster --services simple-backend-service

# Check task status
aws ecs describe-tasks --cluster simple-backend-cluster --tasks $(aws ecs list-tasks --cluster simple-backend-cluster --service simple-backend-service --query 'taskArns[0]' --output text)

# View logs
aws logs tail /ecs/simple-backend --follow
```

## 🧹 Cleanup
```bash
# Automated cleanup
./cleanup.sh
```

## 📍 Important URLs

- **Frontend**: `http://your-bucket-name.s3-website-us-east-1.amazonaws.com`
- **Backend**: `http://YOUR_ECS_PUBLIC_IP:8080`
- **Backend Test**: `http://YOUR_ECS_PUBLIC_IP:8080/test`
- **Frontend Test Page**: `http://your-bucket-name.s3-website-us-east-1.amazonaws.com/test.html`

## 🔍 Troubleshooting

### Backend Issues
```bash
# Check ECS logs
aws logs tail /ecs/simple-backend --follow

# Check task definition
aws ecs describe-task-definition --task-definition simple-backend-task

# Check service events
aws ecs describe-services --cluster simple-backend-cluster --services simple-backend-service --query 'services[0].events'
```

### Frontend Issues
```bash
# Check S3 bucket policy
aws s3api get-bucket-policy --bucket your-bucket-name

# Test CORS
curl -H "Origin: http://your-frontend-domain" \
     -H "Access-Control-Request-Method: POST" \
     -H "Access-Control-Request-Headers: X-Requested-With" \
     -X OPTIONS \
     http://YOUR_ECS_PUBLIC_IP:8080/signup
```

## 📝 Required Manual Updates

1. Update `deploy.sh` with your AWS Account ID and bucket name
2. Update `task-definition.json` with your Account ID and region
3. Update `service-definition.json` with your subnet and security group IDs
4. Update `bucket-policy.json` with your bucket name
5. Update frontend API URLs after getting ECS public IP
