pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/tarunjeetsingh1991/JwtDemo.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081') do taskkill /F /PID %%a
                start cmd /c "java -jar target\\JwtDemo-0.0.1-SNAPSHOT.jar --server.port=8081"
                '''
            }
        }
    }

    post {
        success {
            mail(
                to: 'singh.tarunjeet1991@gmail.com',
                subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Jenkins build succeeded.

				Job Name: ${env.JOB_NAME}
				Build Number: ${env.BUILD_NUMBER}
				Build URL: ${env.BUILD_URL}
				
				The application was built, tested, packaged, and deployed on port 8081.
				"""
            )
        }

        failure {
            mail(
                to: 'singh.tarunjeet1991@gmail.com',
                subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """Jenkins build failed.
	
				Job Name: ${env.JOB_NAME}
				Build Number: ${env.BUILD_NUMBER}
				Build URL: ${env.BUILD_URL}
				
				Please check the Jenkins Console Output for error details.
				"""
            )
        }

        always {
            echo 'Pipeline finished'
        }
    }
}