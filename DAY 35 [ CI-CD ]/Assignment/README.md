# DAY 35 CI/CD Assignment

This repository contains a Spring Boot application with automated CI/CD pipeline using GitHub Actions.

## CI/CD Pipeline Features

The GitHub Actions workflow (`cicd-pipeline.yml`) includes the following steps:

1. **Automated Testing** - Runs Maven tests
2. **Build Artifact** - Creates JAR file using Maven
3. **Docker Image Creation** - Builds Docker image
4. **Docker Hub Deployment** - Pushes image to Docker Hub with latest tag and SHA-based tag

## Path-Based Automation

The pipeline is configured to **only trigger for changes in the `DAY 35 [ CI-CD ]/Assignment/` folder**. This means:

- ✅ Changes to files in `DAY 35 [ CI-CD ]/Assignment/` will trigger the pipeline
- ❌ Changes to other folders (DAY 1, DAY 2, etc.) will NOT trigger this pipeline

## Required Secrets

To use this pipeline, you need to set up the following GitHub repository secrets:

1. `DOCKERHUB_USERNAME` - Your Docker Hub username
2. `DOCKERHUB_TOKEN` - Your Docker Hub access token

### Setting up Docker Hub Secrets:

1. Go to your GitHub repository
2. Navigate to Settings → Secrets and variables → Actions
3. Click "New repository secret"
4. Add both `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN`

## How to Use

1. Make changes to your Spring Boot application in the `DAY 35 [ CI-CD ]/Assignment/` folder
2. Commit and push to the `main` branch
3. GitHub Actions will automatically:
   - Run tests
   - Build the application
   - Create and push Docker image to Docker Hub

## Local Development

To run the application locally:

```bash
# Navigate to the assignment folder
cd "DAY 35 [ CI-CD ]/Assignment"

# Run tests
mvn test

# Build the application
mvn package

# Run the application
java -jar target/Assignment-0.0.1-SNAPSHOT.jar
```

## Docker

To build and run using Docker:

```bash
# Build Docker image
docker build -t day35-cicd-demo .

# Run container
docker run -p 8080:8080 day35-cicd-demo
```

## Application Details

- **Framework**: Spring Boot 3.5.6
- **Java Version**: 21
- **Build Tool**: Maven
- **Port**: 8080 (default Spring Boot port)
