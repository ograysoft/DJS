package com.ogray.djs.model;

/**
 * 	Copyright (C) 2021 Sergey A. Salata. All rights reserved.
 *
 *	This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Callback sent to caller about task progress
 */
@AllArgsConstructor
public class CallbackInfo {
    public static final int TASK_COMPLETED = 1;

    @Setter @Getter
    String node;

    @Setter @Getter
    Long taskId;

    @Setter @Getter
    Integer status;

    public String toString() {
        return "node=["+node+"], taskId="+taskId+", status="+status;
    }
}
