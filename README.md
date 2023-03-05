# ForestSit
![badge](https://img.shields.io/github/downloads/ForestTechMC/ForestSit/total)
![badge](https://img.shields.io/github/last-commit/ForestTechMC/ForestSit)
![badge](https://img.shields.io/badge/platform-spigot-lightgrey)
[![badge](https://img.shields.io/discord/896466173166747650?label=discord)](https://discord.gg/2PpdrfxhD4)
[![badge](https://img.shields.io/github/license/ForestTechMC/ForestSit)](https://github.com/ForestTechMC/ForestSitAPI/blob/master/LICENSE.txt)

Plugin with the best optimization for sitting on a chair or on the ground!

## Table of contents

* [Getting started](#getting-started)
* [Config](#config)
* [License](#license)

## Getting started

1. Turn off server
2. Add ForestSit into /plugins
3. Turn on server
4. And have fun!

### Config

Here you can see the configuration interface

<details>
    <summary>Config</summary>

```yml
#
# ForestSit v1.0.0
#
#   If you find bug or you have an idea for an adjustment, please contact us on
#   https://discord.com/invite/2PpdrfxhD4
#

#
# Messages
#
#  RGB support
#
messages:
noPerm: "{/#c1a005}&lForestSit{/#c8b567} &7You don't have sufficient permissions..."
successSit: "{/#c1a005}&lForestSit{/#c8b567} &7You have successfully sat down..."
alreadySitting: "{/#c1a005}&lForestSit{/#c8b567} &7You're already seated..."
playerInAir: "{/#c1a005}&lForestSit{/#c8b567} &7You're currently in the air!"
configReload: "{/#c1a005}&lForestSit{/#c8b567} &7Config reloading..."
wrongSyntax: "{/#c1a005}&lForestSit{/#c8b567} &7Wrong input..."

#
# Permissions
#
#  If you edit permissions
#  you need to edit it in your permissions manager plugin
#
permissions:
  admin: "forestsit.admin"
  player:
    eventUse: "forestsit.event.stairs"
    commandUse: "forestsit.command"

#
# Events
#
events:
  stairs: true

#
# Data
#
#  Please use "," to split materials
#
data:
  materials: "OAK_STAIRS, SPRUCE_STAIRS"
```
</details>

## License
ForestSit is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/ForestTechMC/ForestSit/blob/master/LICENSE.txt) for more information.