## Technologies
- Java Servlet / JSP
- MySQL
- HTML / CSS / JavaScript
- Apache Tomcat

## Team Workflow
1. Pull latest code before working
2. Create a new branch for each feature
3. Commit with clear message
4. Push branch and create Pull Request

## Branch Rule
main → stable version  
dev → development version  
feature/... → feature development

Git Workflow Guide

To work effectively as a team, we follow a branching workflow.
Branch Structure

main → stable version of the project
dev → development branch for the team
feature/... → branch for developing a specific feature

Example:

main
└── dev
    ├── feature/login
    ├── feature/student-management
    └── feature/class-management

Standard Working Process
Step 1 – Pull latest code
Before starting work, always update the project:

  git checkout dev
  git pull origin dev

Step 2 – Create a feature branch
Create a branch for your task:

  git checkout -b feature/login

Step 3 – Work and commit code
After coding:

  git add .
  git commit -m "feat: add login function"

Step 4 – Push branch to GitHub

  git push origin feature/login

Step 5 – Create Pull Request
Go to the repository on GitHub
Click Pull Requests
Click New Pull Request
Merge feature/... → dev
After the feature is tested and stable, it will later be merged into main.
