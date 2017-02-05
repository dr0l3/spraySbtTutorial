node {
  checkout scm
  docker.image('hseeberger/scala-sbt').inside {
    stage "install dependencies"
        sh "sbt update"

    stage "compile test"
        sh "sbt compile test"
  }

  stage 'package'
  docker.build('droletours/sbtDockerJenkins:1.0').push()

  stage 'deploy'
  sh './deploy.sh'
}