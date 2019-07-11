public class Planet {
    public double xxPos;   // current x position
    public double yyPos;   // current y position
    public double xxVel;   // current velocity in the x direction
    public double yyVel;   // current velocity in the y direction
    public double mass;    // its mass
    public String imgFileName; // the name of the file that corresponds to the image

    public Planet(double xP, double yP, double xV,double yV, double m, String mg) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = mg;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double distanceSquare = (p.xxPos - xxPos) * (p.xxPos - xxPos)
                        + (p.yyPos - yyPos) * (p.yyPos - yyPos);
        return Math.sqrt(distanceSquare);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return (6.67e-11 * mass * p.mass) / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double distance = calcDistance(p);
        double force = calcForceExertedBy(p);
        return (p.xxPos - xxPos) * force / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double distance = calcDistance(p);
        double force = calcForceExertedBy(p);
        return (p.yyPos - yyPos) * force / distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double res = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i])) {
                res += calcForceExertedByX(allPlanets[i]);
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double res = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (!this.equals(allPlanets[i]))
                res += calcForceExertedByY(allPlanets[i]);
        }
        return res;
    }

    public void update(double dt, double fX, double fY) {
        // step1: calculate the acceleration using x and y forces
        double accX = fX / mass;
        double accY = fY / mass;
        // step2: calculate the new velocity of the Planet
        xxVel += dt * accX;
        yyVel += dt * accY;
        // step3: calculate the new position of the Planet
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
