pipeline {

  agent any

    /*
     * Automatic execution
     * Runs approximately every 5 minutes
     */
   triggers {
        cron('10 13 * * *')
    }



    stages {

        stage('Checkout') {

            steps {

                echo 'Checking out automation project...'

                checkout scm
            }
        }

        stage('Clean') {

            steps {

                echo 'Cleaning previous build...'

                bat 'mvn clean'
                
                 // Delete previous reports
                bat 'if exist reports rmdir /s /q reports'

                // Create fresh reports directory
                bat 'mkdir reports'
            }
        }

        stage('Run Sanity Tests') {

            steps {

                echo 'Running Sanity Tests...'

                bat 'mvn test'
            }
        }


        stage('Publish Extent Report') {

            steps {

                echo 'Publishing Extent Report...'

                publishHTML(
                    target: [
                        allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'Test-Report*.html',
                reportName: 'Extent Report',
                reportTitles: "Sanity Automation Report"
                    ]
                )
            }
        }
    }

    post {

        always {

            echo 'Archiving reports...'

            archiveArtifacts(
                artifacts: 'reports/**/*,test-output/**/*',
                allowEmptyArchive: true
            )
        }

        success {

            emailext(

                to: 'shubhjain094323@gmail.com','shubhamwakekar2@gmail.com',

                subject:
                    "SANITY BUILD PASSED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",

                mimeType: 'text/html',

                attachmentsPattern:
                    'reports/Test-Report*.html',

                body: """
                    <html>

                    <body>

                    <h2>Sanity Build Execution Report</h2>

                    <p>Hi Team,</p>

                    <p>
                    The sanity testing has been completed successfully
                    for the latest build.
                    </p>

                    <h3>Build Details</h3>

                    <table border="1"
                           cellpadding="8"
                           cellspacing="0">

                        <tr>
                            <th>Job Name</th>
                            <td>${env.JOB_NAME}</td>
                        </tr>

                        <tr>
                            <th>Build Number</th>
                            <td>#${env.BUILD_NUMBER}</td>
                        </tr>

                        <tr>
                            <th>Status</th>
                            <td><b>PASSED</b></td>
                        </tr>

                    </table>

                    <h3>Overall Result</h3>

                    <p>
                        <b>Sanity testing PASSED.</b>
                    </p>

                    <p>
                        The build is stable.
                    </p>

                    <p>
                        Please find the detailed Extent Report attached.
                    </p>

                    <p>
                        <a href="${env.BUILD_URL}">
                            Open Jenkins Build
                        </a>
                    </p>

                    <br>

                    Regards,<br>
                    <b>Shubham</b><br>
                    QA Engineer

                    </body>

                    </html>
                """
            )
        }

        failure {

            emailext(

                to: 'shubhjain094323@gmail.com', 'shubhamwakekar2@gmail.com',

                subject:
                    "SANITY BUILD FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",

                mimeType: 'text/html',

                attachmentsPattern:
                    'reports/Test-Report*.html',

                body: """
                    <html>

                    <body>

                    <h2>Sanity Build Execution Report</h2>

                    <p>Hi Team,</p>

                    <p>
                    The sanity testing has failed for the latest build.
                    </p>

                    <h3>Build Details</h3>

                    <table border="1"
                           cellpadding="8"
                           cellspacing="0">

                        <tr>
                            <th>Job Name</th>
                            <td>${env.JOB_NAME}</td>
                        </tr>

                        <tr>
                            <th>Build Number</th>
                            <td>#${env.BUILD_NUMBER}</td>
                        </tr>

                    
                        <tr>
                            <th>Status</th>
                            <td><b>FAILED</b></td>
                        </tr>

                    </table>

                   

                    <h3>Action Required</h3>

                    <p>
                        Please check the Jenkins console log and
                        attached Extent Report for failed test cases.
                    </p>

                    <p>
                        <a href="${env.BUILD_URL}">
                            Open Jenkins Build
                        </a>
                    </p>

                    <br>

                    Regards,<br>
                    <b>Shubham</b><br>
                    QA Engineer

                    </body>

                    </html>
                """
            )
        }
    }
}
