**Unofficial Ch4oS fork.** Fixes the client crash when renaming sub-accounts.
Changed by Cha0sCollective on 2026-10-25, based on upstream release commit
`b4cbb96f5cd2fbec80fdbb2f6f3cc3abcd4f6bf5`. Builds use the `ch4os.1` suffix.
Accounts, recipes and permissions are unchanged. In-game testing is still required.

Build with JDK 21 in PowerShell:

```powershell
$env:RELEASE_BUILD = 'true'
$env:REMOVE_DEV_METHODS_ANYWAY = 'true'
.\gradlew.bat :common:test :neoforge:build --no-daemon
```

<div align="center">
  <img src=".idea/icon.png" width="192" height="192">
  <h1>Create Numismatics</h1>

[![discord-plural](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-plural_vector.svg)](https://discord.gg/create-steam-n-rails-706277846389227612)
</div>

## License
Create Numismatics is licensed under the LGPL license. See [LICENSE](LICENSE) for more information.

Certain sections of the code are from the Create mod, which is licensed under the MIT license. See [Create's license](https://github.com/Creators-of-Create/Create/blob/mc1.18/dev/LICENSE) for more information.

Most of the code for annotation based conditional mixins is based on Neruina's Conditional Mixins, which is licensed under the MIT license. See [Neruina's license](https://github.com/Bawnorton/Neruina/blob/multi-version/LICENSE.txt) for more information.
