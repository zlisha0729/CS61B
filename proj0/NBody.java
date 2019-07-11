public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
        // System.out.println("first" + firstItemInFile);
        // System.out.println("second" + secondItemInFile);
        Planet[] planets = new Planet[firstItemInFile];
        for (int i = 0; i < firstItemInFile; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(),
                        in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int len = planets.length;
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
            double xForces[] = new double[len];
            double yForces[] = new double[len];
            for (int i = 0; i < len; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < len; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-universeRadius, universeRadius);
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
