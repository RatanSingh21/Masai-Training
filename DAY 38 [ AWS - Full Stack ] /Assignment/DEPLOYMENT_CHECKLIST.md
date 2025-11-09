# 📋 AWS Console Deployment Checklist

Use this checklist to track your deployment progress through the AWS Console.

## 🏗️ Backend Deployment (ECS)

### ECR Setup
- [ ] Go to ECR Console (https://console.aws.amazon.com/ecr/)
- [ ] Create repository named `simple-backend`
- [ ] Note repository URI: `_________________________`

### Docker Image
Choose one option:
#### Option A: CodeBuild (Recommended)
- [ ] Go to CodeBuild Console
- [ ] Create build project `simple-backend-build`
- [ ] Upload ZIP of simpleBackendApp folder
- [ ] Configure environment (Amazon Linux 2, Standard)
- [ ] Add buildspec.yml content
- [ ] Set environment variables:
  - `AWS_DEFAULT_REGION` = your region
  - `AWS_ACCOUNT_ID` = your account ID
  - `IMAGE_REPO_NAME` = simple-backend
  - `IMAGE_TAG` = latest
- [ ] Run build project
- [ ] Verify image appears in ECR

#### Option B: Local Docker
- [ ] Install Docker on your machine
- [ ] Get ECR login command from console
- [ ] Build image: `docker build -t simple-backend .`
- [ ] Tag and push image to ECR

### ECS Cluster
- [ ] Go to ECS Console (https://console.aws.amazon.com/ecs/)
- [ ] Create cluster `simple-backend-cluster`
- [ ] Choose Fargate infrastructure

### Task Definition
- [ ] Create task definition `simple-backend-task`
- [ ] Configure:
  - [ ] Fargate launch type
  - [ ] CPU: 0.25 vCPU, Memory: 0.5 GB
  - [ ] Container name: `simple-backend-container`
  - [ ] Image URI: (your ECR URI)
  - [ ] Port mapping: 8080
  - [ ] Environment variables: PORT=8080, NODE_ENV=production

### ECS Service
- [ ] Create service `simple-backend-service`
- [ ] Choose your task definition
- [ ] Networking:
  - [ ] Default VPC
  - [ ] Public subnets
  - [ ] Auto-assign public IP: ENABLED
  - [ ] Security group with port 8080 open
- [ ] Note public IP: `_________________________`
- [ ] Test backend: `http://PUBLIC_IP:8080/test`

## 🌐 Frontend Deployment (S3)

### S3 Bucket
- [ ] Go to S3 Console (https://console.aws.amazon.com/s3/)
- [ ] Create bucket with unique name: `_________________________`
- [ ] Same region as ECS
- [ ] Uncheck "Block all public access"
- [ ] Enable ACLs

### Static Website Hosting
- [ ] Go to bucket Properties
- [ ] Enable static website hosting
- [ ] Index document: `index.html`
- [ ] Error document: `index.html`
- [ ] Note website URL: `_________________________`

### Bucket Policy
- [ ] Go to Permissions → Bucket policy
- [ ] Add public read policy (replace bucket name)
- [ ] Save changes

### Update Frontend Code
- [ ] Run: `./update-frontend-console.sh`
- [ ] Enter your ECS public IP
- [ ] Verify script.js and test.html are updated

### Upload Files
- [ ] Go to bucket Objects tab
- [ ] Upload all files from simpleFrontendApp:
  - [ ] index.html
  - [ ] signup.html
  - [ ] welcome.html
  - [ ] script.js (updated)
  - [ ] test.html (updated)

## 🧪 Testing

### Backend Testing
- [ ] Visit: `http://YOUR_ECS_IP:8080/test`
- [ ] Should show: "Welcome to AWS ECR-ECS Backend!"

### Frontend Testing
- [ ] Visit: `http://your-bucket-name.s3-website-region.amazonaws.com`
- [ ] Should show login page

### Integration Testing
- [ ] Visit: `http://your-bucket-name.s3-website-region.amazonaws.com/test.html`
- [ ] Click "Test Backend Connection"
- [ ] Should show success with backend response

### Full Application Testing
- [ ] Go to signup page
- [ ] Create account with email, password, and profile picture
- [ ] Login with created credentials
- [ ] View welcome page with profile picture

## 📸 Documentation

Take screenshots of:
- [ ] Backend test URL working
- [ ] Frontend main page
- [ ] Frontend test page showing successful backend connection
- [ ] Successful signup/login flow

## 🎯 Final URLs

**Backend URL**: `http://___________________:8080`
**Frontend URL**: `http://_____________________.s3-website-_________.amazonaws.com`

## 🧹 Cleanup (When Done)

- [ ] ECS: Delete service → Delete cluster
- [ ] ECR: Delete repository
- [ ] S3: Empty bucket → Delete bucket
- [ ] CloudWatch: Delete log group
- [ ] EC2: Delete security group

## 📞 Need Help?

If you encounter issues:
1. Check the AWS Console Deployment Guide for detailed steps
2. Verify all configurations match the checklist
3. Check CloudWatch logs for ECS service issues
4. Ensure security group allows port 8080 traffic
5. Verify S3 bucket policy allows public read access
