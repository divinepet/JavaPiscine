public class UserIdsGenerator {
    private static Integer id;
    private UserIdsGenerator(){
        id = 0;
    }
    private static class UserIdsGeneratorHOLDER {
        public static final UserIdsGenerator HOLDER_INSTANCE = new UserIdsGenerator();
    }
    public static UserIdsGenerator getInstance() {
        return UserIdsGeneratorHOLDER.HOLDER_INSTANCE;
    }
    public Integer generateId()
    {
        id += 1;
        return id;
    }
}
