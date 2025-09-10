pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = "swaglabs-tests"
        DOCKER_IMAGE_TAG  = "latest"
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building the Docker image..."
                    docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", ".")
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    echo "Running tests inside a Docker container..."
                    // Run tests against a Selenium Grid running in Docker.
                    // The 'docker.withRun' makes services on the same Docker network available.
                    // We use the 'remote' Maven profile to point to the grid.
                    docker.withRun("-e BROWSER=chrome -e SELENIUM_GRID_URL=http://selenium-hub:4444/wd/hub --network host ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", 'mvn clean test -P remote') { c ->
                        // This block can be used to interact with the running container if needed.
                        // For now, we just wait for it to finish.
                    }
                }
            }
        }

        stage('Generate & Archive Allure Report') {
            steps {
                // The Allure Jenkins plugin will automatically find the results
                // and generate the report.
                allure includeProperties: false, jdk: '', results: [[path: 'test-outputs/allure-results']]
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
            // Clean up workspace or Docker images if needed
        }
    }
}