node {
  deleteDir()
  checkout scm
  sh "sbt update"

  def testImage = docker.image('hseeberger/scala-sbt')
  testImage.pull()
  testImage.inside ["-v ~/.m2:/root/.m2:rw", "-v ~/.ivy2:/root/.ivy2:rw"] {
    checkout scm

    stage "install dependencies"
       sh "sbt update"

    stage "compile test"
       sh "sbt compile test"
  }

  stage "package"
  sh "sbt assembly"


  def deployImage = docker.build "dr0l3/testsbt:${env.BUILD_NUMBER}"

  docker.withRegistry("https://docker.io", "docker-registry-login") {
    deployImage.push "latest"
  }

  stage "deploy"
  sh './deploy.sh'
}