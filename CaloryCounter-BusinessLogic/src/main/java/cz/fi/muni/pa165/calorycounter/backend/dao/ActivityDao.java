/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.backend.dao;

import cz.fi.muni.pa165.calorycounter.backend.model.Activity;

/**
 *
 * @author Martin Bryndza (martin.bryndza)
 */
public interface ActivityDao extends Dao<Activity> {

    Activity get(String name);
}
