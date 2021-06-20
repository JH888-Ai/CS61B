//Create a class to run the simulatinon of Planets
class NBody{

	//parse a txt file to find the radius of a planet
	public static double readRadius(String fileName){
		In in =new In(fileName);

		int numberOfPlanets=in.readInt();
		double radiusOfUniverse=in.readDouble();

		return radiusOfUniverse;

	}

	//parse a txt file to return the an array of objects
	//with instance variable values extracted
	public static Planet[] readPlanets(String fileName){
		In in =new In(fileName);

		int numberOfPlanets=in.readInt();
		double radiusOfUniverse=in.readDouble();

		Planet[] planets = new Planet[numberOfPlanets];
		for (int i=0;i<numberOfPlanets;i++){

			double xxPos=in.readDouble();
			double yyPos=in.readDouble();
			double xxVel=in.readDouble();
			double yyVel=in.readDouble();
			double mass=in.readDouble();
			String imgFileName= in.readString();
			planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return planets;
	}

	//main method to draw the initial universe
	public static void main(String[] args){
		//step 1: collecting all needed input from command line
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double scale=readRadius(filename);
		Planet[] planets=readPlanets(filename);

		//step 2: drawing the background
		
		StdDraw.setXscale(-scale,scale);
		StdDraw.setYscale(-scale,scale);
		StdDraw.picture(0.0,0.0,"images/starfield.jpg");

		//step 3: draw static planets
		for (int i=0;i<planets.length;i++){
			planets[i].draw();
		}

		//step 4:create an animation
		StdDraw.enableDoubleBuffering();

		double[] xForces=new double[planets.length];
		double[] yForces=new double[planets.length];
		
		for (double time=0;time<=T;time=time+dt){
			for (int i=0;i<planets.length;i++){

				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.picture(0.0,0.0,"images/starfield.jpg");
			for (int i=0;i<planets.length;i++){
			
				planets[i].update(dt,xForces[i],yForces[i]);
				planets[i].draw();

			}
			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", scale);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName); 

		}
	}
}