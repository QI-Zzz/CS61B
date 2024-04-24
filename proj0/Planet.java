public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;

    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet newp) {
        return Math.sqrt((newp.xxPos - xxPos) * (newp.xxPos - xxPos) + (newp.yyPos - yyPos) * (newp.yyPos - yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return (G * p.mass * mass) / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        if (p.xxPos >= xxPos) {
            return force * (p.xxPos - xxPos) / distance;
        }
        return force * (xxPos - p.xxPos) / distance;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p);
        double distance = calcDistance(p);
        if (p.yyPos >= yyPos) {
            return force * (p.yyPos - yyPos) / distance;
        }
        return force * (yyPos - p.yyPos) / distance;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double totalForce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            totalForce += calcForceExertedByX(planet);
        }
        return totalForce;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double totalForce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            }
            totalForce += calcForceExertedByY(planet);
        }
        return totalForce;
    }
    public void update(double dt,double fX,double fY){
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
    public void draw(){
        StdDraw.picture(xxPos, yyPos,"images/"+imgFileName);
    }

}