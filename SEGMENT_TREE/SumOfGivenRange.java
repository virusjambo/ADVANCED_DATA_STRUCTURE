

public class SumOfGivenRange {
    static int segTree[];

    static int mid(int a, int b) {
        return (a + b) / 2;


    }
    static int getSum(int a[], int ss, int se,int qs,int qe, int index) {

        if (qs<=ss &&  qe>=se) {
               return segTree[index];
        }

        if (se < qs || ss > qe)
            return 0;

        int mid = mid(ss, se);
        return  getSum(a, ss, mid,qs,qe, 2 * index + 1) + getSum(a, mid + 1, se,qs,qe, 2 * index + 2);


    }
    static int segmentTree(int a[], int ss, int se, int index) {
        if (ss > se)
            return 0;
        if (ss == se) {
            segTree[index] = a[ss];
            return segTree[index];
        }
        int mid = mid(ss, se);
        segTree[index] = segmentTree(a, ss, mid, 2 * index + 1) + segmentTree(a, mid + 1, se, 2 * index + 2);
        return segTree[index];

    }

    public static void main(String[] args) {
        int a[] = {4, 5, 2, 1, 3, 6};
        int h = (int) Math.ceil(Math.log(a.length) / Math.log(2));


        int n = (int) (2 * Math.pow(2, h) - 1);
        segTree = new int[2 * n - 1];
        segmentTree(a, 0, a.length - 1, 0);
        System.out.println(getSum(a,0,a.length-1,1,3,0));
        for (int i = 0; i < n; i++)
            System.out.println(segTree[i]);
    }

}
