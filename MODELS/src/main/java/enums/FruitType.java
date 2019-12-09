package enums;

public enum FruitType
{
    Apple{
        @Override
        public int getScoreValue(){
            return 100;
        }
    },
    Banana{
        @Override
        public int getScoreValue(){
            return 250;
        }
    },
    Cherry{
        @Override
        public int getScoreValue(){
            return 500;
        }
    };

    public abstract int getScoreValue();
}
