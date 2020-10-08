float xPos;
float yPos;

float vx;
float vy;

float radius;
boolean inPlayMode;
int pickedCircle;


int Size = 7;//size of the arrays
boolean [] circmove = new boolean[Size];
float [] diameters = new float[Size];
float[] xps = new float[Size];
float[] yps = new float[Size];
color[] colour =new color[Size];

int hits;
int points;

int numGreen = 0;
int wins = 0;
boolean win = false;

import processing.sound.*;
SoundFile boing, blop;

void setup()
{
  size(1000, 1000);
  boing = new SoundFile(this, "boing.wav");
  blop = new SoundFile(this, "blop.wav");
  radius = 15;

  background(255);
  textSize(30);

  xPos = width - radius;
  yPos = height - radius;

  for (int i=0; i<Size; i =i+1) {
    xps[i]= random(1, width);
    yps[i] = random(1, height);
    diameters[i] = random(30, 100);
    color [] colours = {#008335, #A00F1B};
    int c = int(random(colours.length));
    colour[i] = colours[c];

    if (colour[i] == #008335) {
      numGreen ++;
    }
  }
  println(numGreen);
  pickedCircle=-1;


  inPlayMode = false;
}

void keyPressed() {
  if (inPlayMode) {
  } else if (key == ' ') {
    vx = (mouseX - xPos) / 50;
    vy = (mouseY - yPos) / 50;
    inPlayMode = true;
  }
}

void mousePressed() {

  for (int i =0; i<Size; i =i+1) {
    if ((dist(mouseX, mouseY, xps[i], yps[i]))<(diameters[i]/2)) {
      circmove[i] =true;
      pickedCircle =i;
    }
  }
}

void mouseReleased() {
  for (int i =0; i<Size; i =i+1) {
    circmove[i] =false;
    pickedCircle =-1;
  }
}
void mouseDragged() {
  if (pickedCircle >= 0)
  {
    xps[pickedCircle] = mouseX;
    yps[pickedCircle] = mouseY;
  }
}


void draw()
{
  background(254, 244, 232);
  for (int i =0; i<Size; i =i+1) {
    fill(255, 0, 0);
    ellipseMode(CENTER);
    fill(colour[i]);
    ellipse(xps[i], yps[i], diameters[i], diameters[i]);
  }

  if (inPlayMode)
  {
    moveBall();
    drawBall();
    bounceOffWalls();
    checkPegCollsion();
  } else
  {
    drawBall();
    drawAimingLine();
  }

  if (mousePressed) {
    inPlayMode = false;
  }

  fill(0);
  ;
  textAlign(LEFT,LEFT);
  text("Points:"+ points, 0, 30);
  text("Hits:"+ hits, 0, 60);

  if (hits >=5) {
    text("GAME OVER!", width/2, height/2);
    frameRate(0);
  }

  if (numGreen==points) {
    win =true;
  }
  if (win) {
    textAlign(CENTER,CENTER);
    text("YOU WIN \n Click to play again", width/2, height/2);
    vx =0;
    vy=0;
    inPlayMode = false;
    ;
  }


  if (win&&mousePressed&&!inPlayMode) {
    numGreen = 0;
    for (int i=0; i<Size; i =i+1) {
      xps[i]= random(1, width);
      yps[i] = random(1, height);
      diameters[i] = random(30, 100);
      color [] colours = {#008335, #A00F1B};
      int c = int(random(colours.length));
      colour[i] = colours[c];
      inPlayMode = true;

      if (colour[i] == #008335) {
        numGreen ++;
      }
      points = 0;

      win =false;
    }
  }

  
}


void drawAimingLine() {
  stroke(0);
  line(xPos, yPos, mouseX, mouseY);
}


void drawBall() {

  ellipseMode(CENTER);
  fill(#4AC0F5);
  ellipse(xPos, yPos, 2 * radius, 2 * radius);
}

void checkPegCollsion() {
  for (int i=0; i<Size; i =i+1) {
    if (dist(xPos, yPos, xps[i], yps[i]) <=(radius+(diameters[i])/2)) {
      if (colour[i] == #008335) {
        xps[i] = 1000000;
        yps[i]=1000000;
        points++;
        println(numGreen);
        blop.play();
      }

      if (colour[i] == #A00F1B) {
        hits +=1;
        vx = -vx;
        vy = -vy;
      }
    }
  }
}


void moveBall() {
  xPos = xPos + vx;
  yPos = yPos + vy;
}


void bounceOffWalls()
{
  if (xPos - radius < 0 || xPos + radius > width) {
    vx = -vx;
    boing.play();
  }

  if (yPos - radius < 0 || yPos + radius > height) {
    vy = -vy;
    boing.play();
  }
}
