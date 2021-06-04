package com.mononoke.common.item

import com.mononoke.api.item.BaseMononokeItem

class Hitodama : BaseMononokeItem(Settings().maxCount(64).fireproof())