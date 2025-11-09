#!/bin/bash

# Frontend URL Updater for Console Deployment
# This script helps you update the API URLs in your frontend files

echo "🔄 Frontend API URL Updater"
echo "=========================="
echo ""

# Get the ECS public IP from user
echo "After deploying your ECS service, you'll get a public IP address."
echo -n "Enter your ECS public IP address (e.g., 52.23.45.67): "
read ECS_PUBLIC_IP

if [ -z "$ECS_PUBLIC_IP" ]; then
    echo "❌ No IP address provided. Exiting."
    exit 1
fi

API_URL="http://${ECS_PUBLIC_IP}:8080"

echo ""
echo "🔄 Updating frontend files with backend URL: $API_URL"

# Update script.js
if [ -f "simpleFrontendApp/script.js" ]; then
    # Create backup
    cp simpleFrontendApp/script.js simpleFrontendApp/script.js.backup

    # Update the API URL
    sed -i '' "s|http://YOUR_ECS_PUBLIC_IP:8080|${API_URL}|g" simpleFrontendApp/script.js
    echo "✅ Updated script.js"
else
    echo "❌ script.js not found"
fi

# Update test.html
if [ -f "simpleFrontendApp/test.html" ]; then
    # Create backup
    cp simpleFrontendApp/test.html simpleFrontendApp/test.html.backup

    # Update the API URL
    sed -i '' "s|http://YOUR_ECS_PUBLIC_IP:8080|${API_URL}|g" simpleFrontendApp/test.html
    echo "✅ Updated test.html"
else
    echo "❌ test.html not found"
fi

echo ""
echo "🎉 Frontend files updated successfully!"
echo ""
echo "📝 Next steps:"
echo "1. Go to your S3 bucket in the AWS Console"
echo "2. Delete the old script.js and test.html files"
echo "3. Upload the updated files from simpleFrontendApp folder"
echo "4. Test your frontend at: http://YOUR_BUCKET_NAME.s3-website-REGION.amazonaws.com"
echo ""
echo "🔗 Your backend URL is: $API_URL"
echo "🧪 Test your backend at: ${API_URL}/test"

# Ask if user wants to create a zip file for easy upload
echo ""
echo -n "Would you like me to create a zip file for easy S3 upload? (y/n): "
read CREATE_ZIP

if [ "$CREATE_ZIP" = "y" ] || [ "$CREATE_ZIP" = "Y" ]; then
    cd simpleFrontendApp
    zip -r ../frontend-files.zip *
    cd ..
    echo "✅ Created frontend-files.zip - you can upload this to S3 and extract it"
fi

echo ""
echo "✨ Done! Your frontend is ready for deployment."
