public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	//Contructor of Planet
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;

	}

	//Contructor of Planet that is a copy of the Planet object
	public Planet(Planet p){
		this.xxPos=p.xxPos;
		this.yyPos=p.yyPos;
		this.xxVel=p.xxVel;
		this.yyVel=p.yyVel;
		this.mass=p.mass;
		this.imgFileName=p.imgFileName;

	}

	//method that calculates the distance between two planets
	//there is a me planet (created by the test file before calling calcDistance)
	//therefore we only need to pass on the new planet p1 as parameter for this method
	public double calcDistance(Planet p1){
		return Math.sqrt((p1.xxPos-xxPos)*(p1.xxPos-xxPos)+(p1.yyPos-yyPos)*(p1.yyPos-yyPos));

	}

	//method that calculates the force between two planets
	//Math.pow (positive, positive)
	public double calcForceExertedBy (Planet p1){
		return 6.67/Math.pow(10,11)*mass*p1.mass/(calcDistance(p1)*calcDistance(p1));

	}

	//method that calculates the force between two planets, in x component
	public double calcForceExertedByX (Planet p1){
		return calcForceExertedBy(p1)*(p1.xxPos-xxPos)/calcDistance(p1);
	}

	//method that calculates the force between two planets, in y component
	public double calcForceExertedByY (Planet p1){
		return calcForceExertedBy(p1)*(p1.yyPos-yyPos)/calcDistance(p1);
	}

	//method that calculates the net force between many planets, in x component
	// the format to take in objects as array is (ClassName[] objectsName)
	public double calcNetForceExertedByX (Planet[] planets){
		double netforceX=0;
		for (int i=0;i<planets.length;i++){
			if(this==planets[i]){
				continue;
			}
			else{
				netforceX=netforceX+calcForceExertedByX(planets[i]);
			}
		}
		return netforceX;
	}

	public double calcNetForceExertedByY (Planet[] planets){
		double netforceY=0;
		for (int i=0;i<planets.length;i++){
			if(this==planets[i]){
				continue;
			}
			else{
				netforceY=netforceY+calcForceExertedByY(planets[i]);
			}
		}
		return netforceY;

	}

	//Update the position and velocity due to time and force (accleration)
	public void update (double dt, double fX, double fY){
		double acclerationX=fX/mass;
		double acclerationY=fY/mass;
		xxVel=xxVel+dt*acclerationX;
		yyVel=yyVel+dt*acclerationY;
		xxPos=xxPos+dt*xxVel;
		yyPos=yyPos+dt*yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);

	}



}