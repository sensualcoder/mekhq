/*
 * Copyright (c) 2017 The MegaMek Team. All rights reserved.
 *
 * This file is part of MekHQ.
 *
 * MekHQ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MekHQ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MekHQ. If not, see <http://www.gnu.org/licenses/>.
 */
package mekhq.campaign.event;

import java.util.Objects;

import megamek.common.event.MMEvent;
import mekhq.campaign.personnel.Person;
import mekhq.campaign.work.IPartWork;

/**
 * Triggered by an attempt to perform a repait.
 *
 */
public class PartWorkEvent extends MMEvent {

    private final Person tech;
    private final IPartWork partWork;

    public PartWorkEvent(Person tech, IPartWork partWork) {
        this.tech = Objects.requireNonNull(tech);
        this.partWork = Objects.requireNonNull(partWork);
    }

    public Person getTech() {
        return tech;
    }

    public IPartWork getPartWork() {
        return partWork;
    }

}
