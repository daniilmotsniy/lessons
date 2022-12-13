const M = 6, N = 7;
const n0 = 3, m0 = 3;
const p1 = 0.2, p2 = 0.3, p3 = 0.8, p4 = 0.2;
const MOVES_COUNT = 10_000_000;

class Point {
    // point modeling

    constructor(x0, y0) {
        this.x = x0;
        this.y = y0;
        this.is_stopped = false;
    }

    go() {
        let rand = Math.random();
        if (rand < p1) {
            this.y++;
        } else if (rand < p1 + p2) {
            this.y--;
        } else if (rand < p1 + p2 + p3) {
            this.x++;
        } else if (rand < p1 + p2 + p3 + p4) {
            this.x--;
        } else {
            this.is_stopped = true;
        }
    }
}

class Solution {
    // used for resolving

    constructor() {
        this.north = 0;
        this.south = 0;
        this.west = 0;
        this.east = 0;
        this.stop = 0;
    }

    resolve (){
        for (let i = 0; i < MOVES_COUNT; i++) {
            let point = new Point(n0, m0);
            while (point.x < M && point.x > 0 && point.y < N && point.y > 0 && !point.is_stopped) {
                point.go();
            }
            if (point.is_stopped) {
                this.stop++;
            } else {
                if (point.y === M) {
                    this.north++;
                } else if (point.y === 0) {
                    this.south++;
                } else if (point.x === N) {
                    this.east++;
                } else if (point.x === 0) {
                    this.west++;
                }
            }
        }
    }

    printResults(){
        console.log("Північ: " + this.north / MOVES_COUNT);
        console.log("Південь: " + this.south / MOVES_COUNT);
        console.log("Схід: " + this.east / MOVES_COUNT);
        console.log("Захід: " + this.west / MOVES_COUNT);
        console.log("Зупинено: " + this.stop / MOVES_COUNT);
    }

S
}

// init and resolving
let s = new Solution();
s.resolve();
s.printResults();


