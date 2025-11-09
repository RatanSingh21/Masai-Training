#!/bin/bash

# Deployment Validation Script
# This script validates that your deployment is working correctly

# Configuration
BACKEND_URL=""
FRONTEND_URL=""

echo "🔍 AWS Full-Stack Deployment Validator"
echo "=====================================\n"

# Get URLs from user if not provided
if [ -z "$BACKEND_URL" ]; then
    echo -n "Enter your backend URL (e.g., http://52.23.45.67:8080): "
    read BACKEND_URL
fi

if [ -z "$FRONTEND_URL" ]; then
    echo -n "Enter your frontend URL (e.g., http://mybucket.s3-website-us-east-1.amazonaws.com): "
    read FRONTEND_URL
fi

echo "\n🧪 Running validation tests...\n"

# Test 1: Backend health check
echo "1️⃣ Testing backend health..."
BACKEND_RESPONSE=$(curl -s -w "%{http_code}" -o /tmp/backend_response "$BACKEND_URL/test" 2>/dev/null)
BACKEND_STATUS=${BACKEND_RESPONSE: -3}

if [ "$BACKEND_STATUS" = "200" ]; then
    BACKEND_CONTENT=$(cat /tmp/backend_response)
    echo "   ✅ Backend is responding: $BACKEND_CONTENT"
    BACKEND_OK=true
else
    echo "   ❌ Backend is not responding (Status: $BACKEND_STATUS)"
    BACKEND_OK=false
fi

# Test 2: Frontend accessibility
echo "\n2️⃣ Testing frontend accessibility..."
FRONTEND_RESPONSE=$(curl -s -w "%{http_code}" -o /dev/null "$FRONTEND_URL" 2>/dev/null)
FRONTEND_STATUS=${FRONTEND_RESPONSE: -3}

if [ "$FRONTEND_STATUS" = "200" ]; then
    echo "   ✅ Frontend is accessible"
    FRONTEND_OK=true
else
    echo "   ❌ Frontend is not accessible (Status: $FRONTEND_STATUS)"
    FRONTEND_OK=false
fi

# Test 3: CORS check
echo "\n3️⃣ Testing CORS configuration..."
CORS_RESPONSE=$(curl -s -w "%{http_code}" \
    -H "Origin: $FRONTEND_URL" \
    -H "Access-Control-Request-Method: POST" \
    -H "Access-Control-Request-Headers: Content-Type" \
    -X OPTIONS \
    "$BACKEND_URL/signup" 2>/dev/null)
CORS_STATUS=${CORS_RESPONSE: -3}

if [ "$CORS_STATUS" = "200" ] || [ "$CORS_STATUS" = "204" ]; then
    echo "   ✅ CORS is properly configured"
    CORS_OK=true
else
    echo "   ❌ CORS might not be properly configured (Status: $CORS_STATUS)"
    CORS_OK=false
fi

# Test 4: Frontend test page
echo "\n4️⃣ Testing frontend test page..."
TEST_PAGE_RESPONSE=$(curl -s -w "%{http_code}" -o /dev/null "$FRONTEND_URL/test.html" 2>/dev/null)
TEST_PAGE_STATUS=${TEST_PAGE_RESPONSE: -3}

if [ "$TEST_PAGE_STATUS" = "200" ]; then
    echo "   ✅ Frontend test page is accessible"
    TEST_PAGE_OK=true
else
    echo "   ❌ Frontend test page is not accessible (Status: $TEST_PAGE_STATUS)"
    TEST_PAGE_OK=false
fi

# Summary
echo "\n📊 VALIDATION SUMMARY"
echo "===================="

if [ "$BACKEND_OK" = true ] && [ "$FRONTEND_OK" = true ] && [ "$CORS_OK" = true ] && [ "$TEST_PAGE_OK" = true ]; then
    echo "🎉 ALL TESTS PASSED! Your deployment is working correctly."
    echo "\n📍 Your application URLs:"
    echo "   🔗 Frontend: $FRONTEND_URL"
    echo "   🔗 Backend: $BACKEND_URL"
    echo "   🔗 Test Page: $FRONTEND_URL/test.html"

    echo "\n✅ Next steps:"
    echo "   1. Visit $FRONTEND_URL/test.html to test backend connection"
    echo "   2. Try the signup/login flow at $FRONTEND_URL"
    echo "   3. Verify profile picture upload functionality"

    exit 0
else
    echo "❌ Some tests failed. Please check the following:"

    if [ "$BACKEND_OK" = false ]; then
        echo "   - Backend: Check ECS service status and CloudWatch logs"
        echo "     Command: aws logs tail /ecs/simple-backend --follow"
    fi

    if [ "$FRONTEND_OK" = false ]; then
        echo "   - Frontend: Check S3 bucket policy and static website hosting"
        echo "     Command: aws s3api get-bucket-website --bucket YOUR_BUCKET_NAME"
    fi

    if [ "$CORS_OK" = false ]; then
        echo "   - CORS: Check if backend is properly handling CORS headers"
    fi

    if [ "$TEST_PAGE_OK" = false ]; then
        echo "   - Test page: Check if test.html is uploaded to S3"
    fi

    exit 1
fi

# Cleanup temp files
rm -f /tmp/backend_response
