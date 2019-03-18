#!/usr/bin/env groovy

pipeline {
    agent any

    environment {
        APPLICATION_NAME = 'syfosmrestmock'
        DOCKER_SLUG = 'syfo'
    }

    stages {
        stage('initialize') {
            steps {
                init action: 'default'
                script {
                    sh(script: './gradlew clean')
                    def applicationVersionGradle = sh(script: './gradlew -q printVersion', returnStdout: true).trim()
                    env.APPLICATION_VERSION = "${applicationVersionGradle}-${env.COMMIT_HASH_SHORT}"
                    init action: 'updateStatus', applicationName: env.APPLICATION_NAME, applicationVersion: env.APPLICATION_VERSION
                }
            }
        }
        stage('build') {
            steps {
                sh './gradlew build -x test'
            }
        }
        stage('run tests (unit & intergration)') {
            steps {
                sh './gradlew test'
                slackStatus status: 'passed'
            }
        }
        stage('create uber jar') {
            steps {
                sh './gradlew shadowJar'
            }
        }
        stage('deploy to production') {
            steps {
                deployApp action: 'kubectlDeploy', cluster: 'prod-fss'
            }
        }
    }
    post {
        always {
            postProcess action: 'always'
        }
        success {
            postProcess action: 'success'
        }
        failure {
            postProcess action: 'failure'
        }
    }
}
