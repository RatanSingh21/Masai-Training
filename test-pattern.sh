#!/bin/bash

# Test script to verify the DAY 35 pattern matching logic
echo "Testing DAY 35 Assignment folder change detection..."

cd "/Users/RATANS/Documents/HDFCLIFE MASAI TRAINING"

# Get the list of changed files in the last commit
CHANGED_FILES=$(git --no-pager diff --name-only HEAD^ HEAD 2>/dev/null || git --no-pager show --name-only --format="" HEAD 2>/dev/null || echo "")

echo "Files changed in last commit:"
echo "$CHANGED_FILES"
echo ""

# Test the pattern matching
if echo "$CHANGED_FILES" | grep -q "^DAY 35 \[ CI-CD \]/Assignment/"; then
    echo "✅ RESULT: DAY 35 Assignment files detected - Pipeline should run"
else
    echo "❌ RESULT: No DAY 35 Assignment files detected - Pipeline should be skipped"
fi

echo ""
echo "Testing with sample DAY 35 file path:"
TEST_PATH="DAY 35 [ CI-CD ]/Assignment/src/main/java/com/example/cicddemo/controller/HelloController.java"
if echo "$TEST_PATH" | grep -q "^DAY 35 \[ CI-CD \]/Assignment/"; then
    echo "✅ Pattern matching works for: $TEST_PATH"
else
    echo "❌ Pattern matching failed for: $TEST_PATH"
fi
