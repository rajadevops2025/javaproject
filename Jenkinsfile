pipeline {
    agent any
    
    environment {
        APP_NAME = 'my-java-app.jar'
        APP_PORT = '9090'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Stop Existing') {
            steps {
                sh """
                PID=\$(lsof -ti tcp:\$APP_PORT) || true
                if [ ! -z "\$PID" ]; then
                    echo "Stopping process on port \$APP_PORT"
                    kill -9 \$PID
                fi
                """
            }
        }

        stage('Deploy') {
            steps {
                sh """
                nohup java -jar target/\$APP_NAME > app.log 2>&1 &
                echo "Application started on port \$APP_PORT"
                """
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Deployment failed!'
        }
    }
}
