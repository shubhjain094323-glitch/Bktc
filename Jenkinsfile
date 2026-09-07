pipeline {

    agent any

    /*
     * Automatic execution
     * Runs every day at 1:20 PM
     */
    triggers {
        cron('20 13 * * *')
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

                /*
                 * Test cases may fail, but Jenkins build
                 * should still remain SUCCESS.
                 */
                bat '''
                    mvn test
                    if %ERRORLEVEL% NEQ 0 (
                        echo ==========================================
                        echo Some TestNG test cases FAILED.
                        echo Jenkins build will remain SUCCESS.
                        echo Please check the Extent Report.
                        echo ==========================================
                        exit /b 0
                    )
                '''
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
                        reportTitles: 'Sanity Automation Report'
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

                to: 'shubhjain094323@gmail.com, shubhamwakekar2@gmail.com',

                subject:
                    "SANITY BUILD COMPLETED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",

                mimeType: 'text/html',

                attachmentsPattern:
                    'reports/Test-Report*.html',

                body: """
                    <html>

                    <body>

                    <h2>Sanity Automation Execution Report</h2>

                    <p>Hi Team,</p>

                    <p>
                    The sanity automation execution has been completed
                    successfully.
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
                            <th>Jenkins Build Status</th>
                            <td><b>SUCCESS</b></td>
                        </tr>

                    </table>

                    <h3>Test Execution Result</h3>

                    <p>
                    The Jenkins build status does not represent the
                    individual TestNG test results.
                    </p>

                    <p>
                    One or more test cases may have failed during execution.
                    Please refer to the attached Extent Report for the
                    actual Passed, Failed and Skipped test case results.
                    </p>

                    <h3>Extent Report</h3>

                    <p>
                    The detailed Extent Report is attached to this email
                    and is also available in Jenkins.
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

                to: 'shubhjain094323@gmail.com, shubhamwakekar2@gmail.com',

                subject:
                    "JENKINS BUILD FAILED - ${env.JOB_NAME} #${env.BUILD_NUMBER}",

                mimeType: 'text/html',

                attachmentsPattern:
                    'reports/Test-Report*.html',

                body: """
                    <html>

                    <body>

                    <h2>Sanity Automation - Jenkins Failure</h2>

                    <p>Hi Team,</p>

                    <p>
                    The Jenkins pipeline itself has failed.
                    This may indicate an infrastructure, configuration,
                    checkout, build or environment issue.
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
                    Please check the Jenkins console log and the
                    attached Extent Report to identify the issue.
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
