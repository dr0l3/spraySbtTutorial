node {

  docker.image('hseeberger/scala-sbt').inside {
    checkout scm

    stage "install dependencies"
        sh "sbt update"

    stage "compile test"
        sh "sbt compile test"
  }.build('droletours:sbtDockerJenkins').push()

  stage 'deploy'
  sh './deploy.sh'
}