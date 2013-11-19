/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.calorycounter.serviceapi;

import cz.fi.muni.pa165.calorycounter.serviceapi.dto.AuthUserDto;
import cz.fi.muni.pa165.calorycounter.serviceapi.dto.UserActivityRecordsDto;

/**
 *
 * @author Zdenek Lastuvka
 */
public interface UserActivityRecordsService {
    // metody ako filterByDuration, date a pod.

    /**
     * Returns UserActivityRecordsDto for user
     *
     * @return UserActivityRecordsDto
     */
    public UserActivityRecordsDto getAllActivityRecords(AuthUserDto authUserDto);
}
