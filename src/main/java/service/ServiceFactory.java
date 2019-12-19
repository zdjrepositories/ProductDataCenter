package service;


public class ServiceFactory {
    public static void getSerice(String service, String id) {
        try {
            if ("Ranges".equals(service)) {
                new RangesService().run(id);
            } else if ("Products".equals(service)) {
                new ProductsService().run(id);
            } else if ("ProductCharacter".equals(service)) {
                new ProductCharacterService().run(id);
            } else if ("Nodetreebean".equals(service)) {
                new NodetreebeanService().run(id);
            }
        }catch (Exception e){
            System.out.println("程序异常："+e.toString());
        }

    }
}
