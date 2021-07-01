/*
 * Copyright (c) 2021 - The MegaMek Team. All Rights Reserved.
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
package mekhq.gui.panes;

import megamek.client.ui.preferences.JTabbedPanePreference;
import megamek.client.ui.preferences.PreferencesNode;
import megamek.common.annotations.Nullable;
import mekhq.campaign.icons.LayeredForceIcon;
import mekhq.campaign.icons.StandardForceIcon;
import mekhq.gui.baseComponents.AbstractMHQScrollPane;

import javax.swing.*;

public class LayeredForceIconCreationPane extends AbstractMHQScrollPane {
    //region Variable Declarations
    private LayeredForceIcon forceIcon;

    private JTabbedPane tabbedPane;
    //endregion Variable Declarations

    //region Constructors
    public LayeredForceIconCreationPane(final JFrame frame, final @Nullable StandardForceIcon forceIcon) {
        super(frame, "LayeredForceIconCreationPane");
        setForceIcon((forceIcon instanceof LayeredForceIcon)
                ? ((LayeredForceIcon) forceIcon).clone() : new LayeredForceIcon());
        initialize();
    }
    //endregion Constructors

    //region Getters/Setters
    public LayeredForceIcon getForceIcon() {
        return forceIcon;
    }

    public void setForceIcon(final LayeredForceIcon forceIcon) {
        this.forceIcon = forceIcon;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(final JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }
    //endregion Getters/Setters

    //region Initialization
    @Override
    protected void initialize() {
        setTabbedPane(new JTabbedPane());
        getTabbedPane().setName("piecesTabbedPane");
    }

    @Override
    protected void setCustomPreferences(PreferencesNode preferences) {
        super.setCustomPreferences(preferences);
        preferences.manage(new JTabbedPanePreference(getTabbedPane()));
    }
    //endregion Initialization

    //region Button Actions
    //endregion Button Actions
}
