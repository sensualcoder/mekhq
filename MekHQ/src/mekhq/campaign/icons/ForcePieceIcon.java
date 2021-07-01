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
package mekhq.campaign.icons;

import megamek.common.annotations.Nullable;
import mekhq.MHQStaticDirectoryManager;
import mekhq.MekHQ;
import mekhq.MekHqConstants;
import mekhq.MekHqXmlUtil;
import mekhq.campaign.icons.enums.LayeredForceIconLayer;
import org.w3c.dom.Node;

import java.awt.*;
import java.io.PrintWriter;

public class ForcePieceIcon extends StandardForceIcon {
    //region Variable Declarations
    private static final long serialVersionUID = -2459583789787266094L;
    public static final String XML_TAG = "forcePieceIcon";
    private LayeredForceIconLayer layer;
    //endregion Variable Declarations

    //region Constructors
    public ForcePieceIcon() {
        this(LayeredForceIconLayer.FRAME, ROOT_CATEGORY, MekHqConstants.LAYERED_FORCE_ICON_DEFAULT_FRAME_FILENAME);
    }

    public ForcePieceIcon(final LayeredForceIconLayer layer, final @Nullable String category,
                          final @Nullable String filename) {
        super(category, filename);
        setLayer(layer);
    }
    //endregion Constructors

    //region Getters/Setters
    public LayeredForceIconLayer getLayer() {
        return layer;
    }

    public void setLayer(final LayeredForceIconLayer layer) {
        this.layer = layer;
    }

    public String getCategoryPath() {
        return hasDefaultCategory() ? getLayer().getLayerPath() : getLayer().getLayerPath() + getCategory();
    }
    //endregion Getters/Setters

    @Override
    public @Nullable Image getBaseImage() {
        // If we can't create the force icon directory, return null
        if (MHQStaticDirectoryManager.getForceIcons() == null) {
            return null;
        }

        try {
            return (Image) MHQStaticDirectoryManager.getForceIcons().getItem(getCategoryPath(), getFilename());
        } catch (Exception e) {
            MekHQ.getLogger().error(e);
            return null;
        }
    }

    //region File I/O
    @Override
    public void writeToXML(final PrintWriter pw, final int indent) {
        writeToXML(pw, indent, XML_TAG);
    }


    @Override
    protected void writeBodyToXML(final PrintWriter pw, int indent) {
        MekHqXmlUtil.writeSimpleXMLTag(pw, indent, "layer", getLayer().name());
        super.writeBodyToXML(pw, indent);
    }

    public static ForcePieceIcon parseFromXML(final Node wn) {
        final ForcePieceIcon icon = new ForcePieceIcon();
        try {
            icon.parseNodes(wn.getChildNodes());
        } catch (Exception e) {
            MekHQ.getLogger().error(e);
            return new ForcePieceIcon();
        }
        return icon;
    }

    @Override
    protected void parseNode(final Node wn) {
        super.parseNode(wn);
        switch (wn.getNodeName()) {
            case "layer":
                setLayer(LayeredForceIconLayer.valueOf(wn.getTextContent().trim()));
                break;
            default:
                break;
        }
    }
    //endregion File I/O

    @Override
    public String toString() {
        return hasDefaultCategory() ? getLayer().getLayerPath() + getFilename()
                : getLayer().getLayerPath() + getCategory() + getFilename();
    }

    @Override
    public boolean equals(final @Nullable Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        } else if (other instanceof ForcePieceIcon) {
            final ForcePieceIcon dOther = (ForcePieceIcon) other;
            return (dOther.getLayer() == getLayer()) && dOther.getCategory().equals(getCategory())
                    && dOther.getFilename().equals(getFilename());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (getLayer().name() + getCategory() + getFilename()).hashCode();
    }

    @Override
    public ForcePieceIcon clone() {
        return new ForcePieceIcon(getLayer(), getCategory(), getFilename());
    }
}
