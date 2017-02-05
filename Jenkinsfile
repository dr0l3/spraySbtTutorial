node {

  def testImage = docker.image('hseeberger/scala-sbt')
  testImage.pull()
  testImage.inside {
    checkout scm

    stage "install dependencies"
       sh "sbt update"

    stage "compile test"
       sh "sbt compile test"
  }


  def deployImage = docker.build('droletours:testsbt')

  docker.withRegistry("https://hub.docker.com", "docker-registry-login") {
    deployImage.push()
  }

  stage "deploy"
  sh './deploy.sh'
}