public class Main {
    public static void main(String[] args) {

        String[][] arr = new String[][] { { "11", "12", "13", "14" }, { "21", "22", "23", "24" },
                { "31", "32", "33", "34" },
                { "41", "42", "43", "44" } };
        try {
            try {
                int result = checkIt(arr);
                System.out.println(result);
            } catch (MyArraySizeException e) {
                System.out.println("The array size must be 4x4 ONLY!");
            }
        } catch (MyArrayDataException e) {
            System.out.println("Wrong datatype!");
            System.out.println("Check the cell: " + e.i + " - " + e.j);
        }

    }

    public static int checkIt(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int flag = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    flag = flag + Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }

        }
        return flag;
    }
}