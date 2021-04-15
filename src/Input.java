class Input {

    int main(String[] args) {

        int num = -2 + 4 - 1;
        int []odd_sum = {0};
        int []even_sum = {0};
        int quotient, remainder;
        boolean testForBoolean = true;
        String[] result = {};

        while(testForBoolean != false) {
            if(num > 100) {
                result[0] = "Sum of even numbers to 100 " + even_sum[0];
                result[1] = "Sum of odd numbers to 100 " + odd_sum[0];
                return 0;
            }
            else {
                quotient = num / 2;
                remainder = num - quotient * 2;

                if(remainder == 0) {
                    even_sum[0] = even_sum[0] + num;
                    num = num + 1;
                }
                else {
                    odd_sum[0] = odd_sum[0] + num;
                    num = num + 1;
                }
            }
        }

        return 0;
    }

}