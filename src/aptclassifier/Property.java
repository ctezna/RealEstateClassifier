package aptclassifier;
/**
 * Class used for creating instances
 * of new properties
 * @Author Carlos F. Tezna
 * @Version Consumer Edition 1.0
 */


public class Property{
        private double[] x;
        private Object y;
        /**receives double array for property characteristics
         *
         */
        public Property(double[] x, Object y){ //object (string) is label of property
            this.x = x;
            this.y = y;
        }
        /**getter for property characteristics
         *
         */
        public double[] getX(){
            return this.x;
        }
        /**getter for property label
         *
         */
        public Object getY(){
            return this.y;
        }
    }

