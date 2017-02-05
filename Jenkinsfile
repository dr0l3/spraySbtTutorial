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


  def deployImage = docker.build('dr0l3/testsbt')



  docker.withRegistry("https://hub.docker.com", "docker-registry-login") {
    deployImage.push("latest")
  }

  stage "deploy"
  sh './deploy.sh'
}