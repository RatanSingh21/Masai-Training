#!/bin/bash

# Script to update frontend API URL after ECS deployment
# Usage: ./update-frontend-url.sh <ECS_PUBLIC_IP>

if [ $# -eq 0 ]; then
    echo "Usage: $0 <ECS_PUBLIC_IP>"
    echo "Example: $0 52.23.45.67"
    exit 1
fi

ECS_PUBLIC_IP=$1
API_URL="http://${ECS_PUBLIC_IP}:8080"

echo "🔄 Updating frontend API URL to: $API_URL"

# Update script.js
sed -i.bak "s|http://YOUR_ECS_PUBLIC_IP:8080|${API_URL}|g" simpleFrontendApp/script.js

# Update test.html
sed -i.bak "s|http://YOUR_ECS_PUBLIC_IP:8080|${API_URL}|g" simpleFrontendApp/test.html

echo "✅ Updated script.js and test.html"
echo "📝 Backup files created with .bak extension"

# If S3 bucket name is provided as second argument, sync to S3
if [ $# -eq 2 ]; then
    BUCKET_NAME=$2
    echo "📤 Uploading updated files to S3..."
    cd simpleFrontendApp
    aws s3 sync . s3://$BUCKET_NAME --delete
    echo "✅ Frontend updated on S3"
fi

echo "🎉 Frontend API URL updated successfully!"
echo "📍 Test your backend at: ${API_URL}/test"
