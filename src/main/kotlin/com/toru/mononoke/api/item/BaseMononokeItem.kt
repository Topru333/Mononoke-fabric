package com.toru.mononoke.api.item

import com.toru.mononoke.common.itemgroup.BaseGroup
import net.minecraft.item.Item

open class BaseMononokeItem(settings: Settings = Settings()) : Item(settings.group(BaseGroup))