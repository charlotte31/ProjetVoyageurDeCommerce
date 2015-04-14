/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voyageurdecommerce.events;

import java.util.EventListener;

/**
 *
 * @author Charlotte
 */
public interface VilleSurvoleeListener extends EventListener {
    public void onVilleSurvolee(EventVilleSurvolee evt);
}
