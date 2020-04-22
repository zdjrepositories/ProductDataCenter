package service;


public class ServiceFactory {
    public static void getSerice(String service, String id) {

            if ("Ranges".equals(service)) {
                new RangesService().run(id);
            } else if ("Products".equals(service)) {
                new ProductsService().run(id);
            } else if ("ProductCharacter".equals(service)) {
                new ProductCharacterService().run(id);
            } else if ("Nodetreebean".equals(service)) {
                new NodetreebeanService().run(id);
            }else if ("Document".equals(service)) {
                new DocumentService().run(id);
            }


    }
}
