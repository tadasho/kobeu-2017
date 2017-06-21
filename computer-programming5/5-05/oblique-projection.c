#include <stdio.h>
#include <math.h>
#define g  9.8
#define v0 49.0
#define theta 50
#define PI 3.141592653589793
int main(void) {
    int n,nend;
    double y,vy,x,vx,t,tend,dt;
    dt   = 0.001;
    tend = 7.66;
    nend = tend/dt;
    vy   = v0 * sin(theta * PI / 180.0);
    vx = v0 * cos(theta * PI / 180.0);
    t = 0.0;
    y = 0.0;
    x = 0.0;

    printf("%10.5f %10.5f %10.5f \n",t, x, y);
    for(n=1; n <= nend; n++){
        t  = dt*n;
        x = x + vx * dt;
        y =y+vy*dt;
        vy = vy - g*dt;
        printf("%10.5f %10.5f %10.5f \n",t, x, y);
    }
    return 0; 
}