# 🌐 AWS Console Deployment Guide

This guide will help you deploy your full-stack application using the AWS web console instead of CLI commands.

## 📋 Prerequisites
- AWS Account with billing enabled
- Your project files ready (which are already prepared)

---

## 🚀 PART 1: Backend Deployment (ECS via Console)

### Step 1: Create ECR Repository

1. **Go to ECR Console**: https://console.aws.amazon.com/ecr/
2. **Click "Create repository"**
3. **Repository name**: `simple-backend`
4. **Visibility**: Private
5. **Click "Create repository"**
6. **Note the repository URI** (something like: `123456789.dkr.ecr.us-east-1.amazonaws.com/simple-backend`)

### Step 2: Build and Push Docker Image

Since you can't use CLI, you have two options:

#### Option A: Use AWS CodeBuild (Recommended)
1. **Go to CodeBuild Console**: https://console.aws.amazon.com/codesuite/codebuild/
2. **Click "Create build project"**
3. **Project name**: `simple-backend-build`
4. **Source provider**: Upload a ZIP file (zip your simpleBackendApp folder)
5. **Environment**: Managed image, Amazon Linux 2, Standard runtime
6. **Service role**: Create new service role
7. **Buildspec**: Insert build commands (see buildspec.yml section below)

#### Option B: Use Local Docker (if you have Docker installed)
1. Open Terminal/Command Prompt
2. Navigate to your simpleBackendApp folder
3. Run these commands (replace YOUR_ACCOUNT_ID and YOUR_REGION):

```bash
# Login to ECR (get this command from ECR console "View push commands")
aws ecr get-login-password --region YOUR_REGION | docker login --username AWS --password-stdin YOUR_ACCOUNT_ID.dkr.ecr.YOUR_REGION.amazonaws.com

# Build image
docker build -t simple-backend .

# Tag image
docker tag simple-backend:latest YOUR_ACCOUNT_ID.dkr.ecr.YOUR_REGION.amazonaws.com/simple-backend:latest

# Push image
docker push YOUR_ACCOUNT_ID.dkr.ecr.YOUR_REGION.amazonaws.com/simple-backend:latest
```

### Step 3: Create ECS Cluster

1. **Go to ECS Console**: https://console.aws.amazon.com/ecs/
2. **Click "Create Cluster"**
3. **Cluster name**: `simple-backend-cluster`
4. **Infrastructure**: AWS Fargate (serverless)
5. **Click "Create"**

### Step 4: Create Task Definition

1. **In ECS Console, click "Task definitions" → "Create new task definition"**
2. **Task definition family**: `simple-backend-task`
3. **Launch type**: AWS Fargate
4. **Operating system**: Linux/X86_64
5. **CPU**: 0.25 vCPU
6. **Memory**: 0.5 GB
7. **Task role**: Create new role or use existing ecsTaskRole
8. **Task execution role**: Create new role or use existing ecsTaskExecutionRole

#### Container Configuration:
- **Container name**: `simple-backend-container`
- **Image URI**: (Your ECR repository URI from Step 1)
- **Port mappings**: Container port `8080`, Protocol `TCP`
- **Environment variables**:
  - `PORT` = `8080`
  - `NODE_ENV` = `production`

#### Logging (Optional but recommended):
- **Log driver**: awslogs
- **awslogs-group**: `/ecs/simple-backend` (create this in CloudWatch first)
- **awslogs-region**: your region
- **awslogs-stream-prefix**: `ecs`

### Step 5: Create Service

1. **In your ECS cluster, click "Create service"**
2. **Launch type**: Fargate
3. **Task definition**: `simple-backend-task:1`
4. **Service name**: `simple-backend-service`
5. **Number of tasks**: `1`
6. **Minimum healthy percent**: `0`
7. **Maximum percent**: `200`

#### Networking:
- **VPC**: Default VPC (or your preferred VPC)
- **Subnets**: Select public subnets
- **Auto-assign public IP**: ENABLED
- **Security group**: Create new security group
  - **Inbound rule**: Type: Custom TCP, Port: 8080, Source: 0.0.0.0/0

8. **Click "Create service"**

### Step 6: Get Backend URL

1. **Go to ECS Console → Clusters → simple-backend-cluster**
2. **Click on the running task**
3. **Note the Public IP address**
4. **Your backend URL**: `http://PUBLIC_IP:8080`
5. **Test it**: `http://PUBLIC_IP:8080/test` should show "Welcome to AWS ECR-ECS Backend!"

---

## 🌐 PART 2: Frontend Deployment (S3 via Console)

### Step 1: Create S3 Bucket

1. **Go to S3 Console**: https://console.aws.amazon.com/s3/
2. **Click "Create bucket"**
3. **Bucket name**: Choose a globally unique name (e.g., `your-name-fullstack-frontend-2024`)
4. **Region**: Same as your ECS cluster
5. **Object Ownership**: ACLs enabled
6. **Block Public Access**: UNCHECK "Block all public access"
7. **Acknowledge the warning**
8. **Click "Create bucket"**

### Step 2: Enable Static Website Hosting

1. **Click on your bucket name**
2. **Go to "Properties" tab**
3. **Scroll down to "Static website hosting"**
4. **Click "Edit"**
5. **Enable static website hosting**
6. **Index document**: `index.html`
7. **Error document**: `index.html`
8. **Click "Save changes"**
9. **Note the website URL** (something like: `http://bucket-name.s3-website-region.amazonaws.com`)

### Step 3: Set Bucket Policy

1. **Go to "Permissions" tab**
2. **Click "Bucket policy" → "Edit"**
3. **Paste this policy** (replace `YOUR_BUCKET_NAME`):

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

4. **Click "Save changes"**

### Step 4: Update Frontend Code

Before uploading, you need to update the API URL in your frontend:

1. **Open `simpleFrontendApp/script.js`**
2. **Replace `http://YOUR_ECS_PUBLIC_IP:8080` with your actual backend URL**
3. **Also update `simpleFrontendApp/test.html` with the same URL**

### Step 5: Upload Frontend Files

1. **Go to "Objects" tab in your S3 bucket**
2. **Click "Upload"**
3. **Add files**: Select all files from `simpleFrontendApp` folder:
   - `index.html`
   - `signup.html` 
   - `welcome.html`
   - `script.js`
   - `test.html`
4. **Click "Upload"**

---

## 🧪 PART 3: Testing Your Deployment

### Test Backend:
- Visit: `http://YOUR_ECS_PUBLIC_IP:8080/test`
- Should show: "Welcome to AWS ECR-ECS Backend!"

### Test Frontend:
- Visit: `http://your-bucket-name.s3-website-region.amazonaws.com`
- Should show the login page

### Test Integration:
- Visit: `http://your-bucket-name.s3-website-region.amazonaws.com/test.html`
- Click "Test Backend Connection"
- Should show success message with backend response

### Test Full Flow:
1. Go to signup page
2. Fill in email, password, and select a profile picture
3. Click signup (should work if you've configured AWS S3 credentials)
4. Try logging in with the same credentials

---

## 🧹 PART 4: Cleanup (To Avoid Charges)

When you're done testing:

### Delete ECS Resources:
1. **ECS Console → Services** → Select service → Actions → Delete
2. **ECS Console → Clusters** → Select cluster → Delete
3. **ECR Console** → Select repository → Delete

### Delete S3 Resources:
1. **S3 Console** → Select bucket → Empty bucket
2. **Then delete the bucket**

### Delete Other Resources:
1. **CloudWatch Logs** → Delete log group `/ecs/simple-backend`
2. **EC2 Console → Security Groups** → Delete the security group you created

---

## 📝 Important Notes

1. **Costs**: ECS Fargate charges for running time, S3 charges for storage and requests
2. **Security**: The setup uses public access - for production, implement proper authentication
3. **Environment Variables**: For S3 uploads to work, you'll need to configure AWS credentials in your backend
4. **Region**: Keep everything in the same AWS region for better performance

---

## 🎯 Expected Deliverables

- **Backend URL**: `http://YOUR_ECS_PUBLIC_IP:8080`
- **Frontend URL**: `http://your-bucket-name.s3-website-region.amazonaws.com`

Take screenshots of both URLs working for your submission!
