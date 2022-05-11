const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(45, window.innerWidth / window.innerHeight, 0.1, 1000 );
const renderer = new THREE.WebGLRenderer()
  
let playing = false, togglePlayBtn;

let pacman, pacGeom, pacbody, pacBodyGeom, pacmanFrames;

let shapes = [], shapeGeoms = [];

let score = 0, scoreElement, scoreIncrement = 1;
let inBonus = false

camera.position.z = -28
camera.position.x = -140
camera.position.y = 4

camera.rotation.y = -1.75

function init() {
  playing = true
  renderer.setSize( 800, 650 );
  document.getElementById("main-screen").style.display = "none"

  document.body.appendChild( renderer.domElement );

  scene.add( new THREE.AmbientLight( 0xff0000, 0.1 ) ); // optional

  // light
  var light = new THREE.PointLight( 0x0000ff, 1 );
  camera.add( light );

  startGame()
  renderer.render(scene, camera)
}

function startGame() {
  scoreElement = document.createElement("span")
  togglePlayBtn = document.createElement("span")
  scoreElement.innerHTML = `${score}`

  togglePlayBtn.innerHTML = "PAUSE"
  togglePlayBtn.id = "toggle-play"

  togglePlayBtn.addEventListener("click", togglePlay)

  scoreElement.id = "score"

  document.body.appendChild(scoreElement)
  document.body.appendChild(togglePlayBtn)

  pacGeom = new THREE.SphereGeometry(1, 32, 6.5, 5.7);
  pacBodyGeom = new THREE.CapsuleGeometry(1, 2, 3, 8);
  
  const material = new THREE.MeshBasicMaterial({ 
    color: 0xffff00,
    side: THREE.DoubleSide,
    opacity: 0.6
  });
  
  pacman = new THREE.Mesh(pacGeom, material);
  pacbody = new THREE.Mesh(pacBodyGeom, material)

  pacman.position.x = -100
  pacbody.position.x = -100

  pacbody.position.y = -3

  pacman.position.z = -26
  pacbody.position.z = -26

  pacman.rotation.y = -1.7
  pacbody.rotation.y = -1.7

  scene.add(pacman);
  scene.add(pacbody)

  addShapes()

  document.addEventListener("keydown", movePacman)

  pacmanAnimation()
}

function movePacman(e) {
  // move according to input
  e = e || window.event;

  switch(e.keyCode) {
    case 37:
      pacman.position.set(pacman.position.x, pacman.position.y, pacman.position.z -2)
      pacbody.position.set(pacbody.position.x, pacbody.position.y, pacbody.position.z -2)
      break;
    case 38:
      pacman.position.set(pacman.position.x, pacman.position.y +2, pacman.position.z)
      pacbody.position.set(pacbody.position.x, pacbody.position.y +2, pacbody.position.z)
      break;
    case 39:
      pacman.position.set(pacman.position.x, pacman.position.y, pacman.position.z +2)
      pacbody.position.set(pacbody.position.x, pacbody.position.y, pacbody.position.z +2)
      break;
    case 40:
      pacman.position.set(pacman.position.x, pacman.position.y -2, pacman.position.z)
      pacbody.position.set(pacbody.position.x, pacbody.position.y -2, pacbody.position.z)
      break;
  }
}

function randRGB() {
  const randRange = () => 0 + Math.floor(Math.random() * (255 - 0 + 1));

  const r = randRange(), 
        g = randRange(),
        b = randRange();
        
  return `rgb(${r},${g},${b})`
}

function addShapes() {
  const sphereG = new THREE.SphereGeometry(1, 32, 16),
        torusG = new THREE.TorusGeometry(1, 0.1, 16, 100),
        octahedronG = new THREE.OctahedronGeometry(1, 0),
        coneG = new THREE.ConeGeometry(1, 1, 32),
        capsuleG = new THREE.CapsuleGeometry( 1, 1, 3, 8 );

  shapeGeoms.push(sphereG, torusG, octahedronG, coneG, capsuleG)

  for (var i = 0; i < 70; i++) {
    const mat = new THREE.MeshBasicMaterial({ color: new THREE.Color(randRGB()) });
    let shape = new THREE.Mesh(shapeGeoms[Math.floor(Math.random()* shapeGeoms.length)], mat)

    shape.position.x = Math.floor(Math.random() * 600) -70;
    shape.position.y = Math.floor(Math.random() * 50) - 20;
    shape.position.z = Math.floor(Math.random() * 18) - 32;

    shapes.push(shape)
    scene.add(shape)

    function shapeAnimation() {
      requestAnimationFrame(shapeAnimation)

      shape.rotation.y += 0.05
      shape.rotation.x += 0.05
      shape.rotation.z += 0.05
    }

    shapeAnimation()

    renderer.render(scene, camera)
  }
}

function detectCollision(object){
  object.geometry.computeBoundingBox();
  pacman.geometry.computeBoundingBox();
  pacbody.geometry.computeBoundingBox();

  object.updateMatrixWorld();
  pacman.updateMatrixWorld();
  pacbody.updateMatrixWorld();
  
  var objBox = object.geometry.boundingBox.clone();
  objBox.applyMatrix4(object.matrixWorld);

  var pacBox = pacman.geometry.boundingBox.clone();
  pacBox.applyMatrix4(pacman.matrixWorld);

  var pacbodyBox = pacbody.geometry.boundingBox.clone();
  pacbodyBox.applyMatrix4(pacman.matrixWorld);

  return objBox.intersectsBox(pacBox) || objBox.intersectsBox(pacbodyBox);
}

function startBonus() {
  let bonusText = document.createElement("span")
  bonusText.id = "bonus"
  bonusText.innerHTML = "2X SCORE MULTIPLIER ACTIVATED"

  document.body.appendChild(bonusText)
}

function finishGame() {
  document.body.innerHTML = ""

  let doneContainer = document.createElement("div")
  doneContainer.id = "done-screen"

  let doneText = document.createElement("span")
  doneText.innerHTML = "Well Done!"
  
  let scoreText = document.createElement("span")
  scoreText.innerHTML = `SCORE: ${score}`

  let replayBtn = document.createElement("button")
  replayBtn.type = "button"
  replayBtn.id = "replayBtn"
  replayBtn.innerHTML = "Play Again"

  replayBtn.addEventListener("click", function() {
    window.location.reload();
  })

  document.body.appendChild(doneContainer)
  doneContainer.appendChild(doneText)
  doneContainer.appendChild(scoreText)
  doneContainer.appendChild(replayBtn)
}

function togglePlay() {
  playing = !playing

  if (!playing) {
    cancelAnimationFrame(pacmanFrames)
    togglePlayBtn.innerHTML = "START"
  } else {
    pacmanAnimation()
    togglePlayBtn.innerHTML = "PAUSE"
  }
}

function pacmanAnimation() {
  pacmanFrames = requestAnimationFrame(pacmanAnimation)
  
  if (pacman.position.x < 565) {
    pacman.position.x += 0.22
    pacbody.position.x += 0.22
    camera.position.x += 0.22

    for (let shape in shapes) {
      if (detectCollision(shapes[shape])) {
        score += scoreIncrement
        scoreElement.innerHTML = `${score}`
      }
    }

    if (pacman.position.x >= 100) {    
      if (!inBonus) {
        scene.background = new THREE.Color(0x0B0B45)
        scoreIncrement = 2
        startBonus()
      }
      
      inBonus = true
    }

    if (pacman.position.x >= 300) {
      if (inBonus) {
        scene.background = new THREE.Color(0x000000)
        scoreIncrement = 1
        document.body.removeChild(document.getElementById("bonus"))
      }

      inBonus = false
    }
  } else {
    finishGame()
    cancelAnimationFrame(pacmanFrames)
  }

  renderer.render(scene, camera)
}

document.getElementById("begin-btn").addEventListener("click", init)
