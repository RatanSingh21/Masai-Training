// Simple backend test without AWS dependencies
import express from "express";
import cors from "cors";

const app = express();
app.use(cors());
app.use(express.json());

// Root endpoint
app.get("/", (req, res) => {
  res.send("Welcome to AWS ECR-ECS Backend!");
});

// Test endpoint
app.get("/test", (req, res) => {
  res.send("Welcome to AWS ECR-ECS Backend!");
});

// Simple health check
app.get("/health", (req, res) => {
  res.json({ status: "ok", timestamp: new Date().toISOString() });
});

const PORT = process.env.PORT || 8080;
app.listen(PORT, () => console.log(`Server running on port ${PORT}`));
