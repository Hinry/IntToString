import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by mrhri on 09.11.2016.
 */
/*Если записать числа от 1 до 5 английскими словами (one, two, three, four, five),
    то используется 3 + 3 + 5 + 4 + 4 = 19 букв.
    Сколько букв понадобится для записи всех чисел от 1 до 1000 (one thousand) включительно?
    Примечание: Не считайте пробелы и дефисы. Например, число 342 (three hundred and forty-two) состоит из 23 букв,
    число 115 (one hundred and fifteen) - из 20 букв.
    Использование "and" при записи чисел соответствует правилам британского английского.
*/
public class Main {


    public static void main(String[] args){

        //Создали массив для записи в него каждого числа прописью
        ArrayList<String> arr2 = new ArrayList();

        //через цикл добавили в него все значения
        for(int i=0; i <= 1000; i++){
            arr2.add(IntToString(i));
        }

        //создали массив чисел. через стрим убрали все пробелы и дефисы у каждоый строки Массива arr2 ,
        // узнали длину каждоый строки и добавили значения в массив чисел list
        int list[] = arr2.stream().map((s)  -> s.replaceAll(" ","").replaceAll("-","").length()).mapToInt(x->x).toArray();

        //создали переменную в которой хранится сумма всех чисел в массиве list с помощью IntStream.sum();
        int total = IntStream.of(list).sum();

        //Вывод резуьтата на экран
        System.out.println(total);

        //проверка работы функции
        System.out.println(IntToString(10));
        System.out.println(IntToString(15));
        System.out.println(IntToString(003));
        System.out.println(IntToString(123));
        System.out.println(IntToString(100));
        System.out.println(IntToString(300));
        System.out.println(IntToString(1000));
        System.out.println(IntToString(999));
        System.out.println(IntToString(-102));

    }

    public static String IntToString(Integer numb){

        String[] zeroTo9 = {"","one","two","three","four","five","six","seven","eight","nine"};
        String[] elevenToNineteen = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
        String[] tens = {"","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};
        String[] hungreds = {"","one hundred","two hundred","three hundred",
                "four hundred","five hundred","six hundred","seven hundred","eight hundred","nine hundred"};
        String[] thous = {"","one thousand"};

        String numb_str = String.valueOf(numb);
        StringBuilder list_numb = new StringBuilder();

        if(numb<0){
            return "Число отрицательное!";
        }
        if(numb_str.length()==1){
            list_numb.append(zeroTo9[numb]);
            return list_numb.toString();
        }
        if(numb_str.length()==2){
            if(numb >= 10 && numb < 20) {
                String[] numb_arr = numb_str.split("");
                int last_n = Integer.parseInt(numb_arr[1]);
                list_numb.append(zeroTo9[0] + elevenToNineteen[last_n]);
                return list_numb.toString();
            }
        }
        if(numb_str.length()==2){
            if(numb >= 20) {
                String[] numb_arr = numb_str.split("");
                int first_n = Integer.parseInt(numb_arr[0]);
                int last_n = Integer.parseInt(numb_arr[1]);
                list_numb.append(tens[first_n] +" "+ zeroTo9[last_n]);
                return list_numb.toString();
            }
        }
        if(numb_str.length()==3){
            if(numb >= 100 || numb <= 999) {
                String[] numb_arr = numb_str.split("");
                int first_n = Integer.parseInt(numb_arr[0]);
                int mid_n = Integer.parseInt(numb_arr[1]);
                int last_n = Integer.parseInt(numb_arr[2]);
                if(mid_n ==0 && last_n ==0){
                    list_numb.append(hungreds[first_n]);
                    return list_numb.toString();
                }
                if(mid_n==0) {
                    list_numb.append(hungreds[first_n] + " and " + zeroTo9[last_n]);
                    return list_numb.toString();
                }
                if(mid_n == 1 && last_n<10){
                    list_numb.append(hungreds[first_n]+ " and "+elevenToNineteen[last_n] );
                    return list_numb.toString();
                }
                if(mid_n > 1 && mid_n<10){
                    list_numb.append(hungreds[first_n]+ " and "+ tens[mid_n]+"-"+ zeroTo9[last_n]);
                    return list_numb.toString();
                }
            }
        }
        if(numb_str.length() == 4){
            if(numb == 1000){
                list_numb.append(thous[1]);
                return list_numb.toString();
            }else{
                return "Максимальный диапазон - 1000";
            }
        }
        return null;
    }

}
