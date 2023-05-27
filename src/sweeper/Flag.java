package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosedBoxes;
    void start(){
        flagMap = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord){
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosedBoxes--;
    }

    void toggleFlagToBox(Coord coord){
        switch (flagMap.get(coord)){
            case FLAGGED -> setClosedToBox(coord);
            case CLOSED -> setFlagToBox(coord);
        }
    }
    private void setClosedToBox(Coord coord){
        flagMap.set(coord, Box.CLOSED);

    }
    private void setFlagToBox(Coord coord){
        flagMap.set(coord, Box.FLAGGED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToCloseBombBox(Coord coord) {
        if(flagMap.get(coord) == Box.CLOSED){
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNoBombToFlaggedSafeBox(Coord coord) {
        if(flagMap.get(coord) == Box.FLAGGED){
            flagMap.set(coord, Box.NOBOMB);
        }
    }
    int getCountOfFlaggedBoxesAround(Coord coord) {
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord)){
            if(flagMap.get(around) == Box.FLAGGED)
                count++;
        }
        return count;
    }
}
