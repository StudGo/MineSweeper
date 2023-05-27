package sweeper;

class Matrix {
    private Box[][] matrix;
    
    Matrix(Box defBox){
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCoords()) {
            matrix[coord.x][coord.y] = defBox;
        }
    }
    Box get (Coord coord){
        if(Ranges.inRange(coord))
            return matrix[coord.x][coord.y];
        return null;
    }
    void set(Coord coord, Box box){
        matrix[coord.x][coord.y] = box;
    }

}
