import os

folder = "./"  # folder containing your files

# Your old names (WITHOUT extensions)
old_names = [
    "sprint_002",
    "sprint_003",
    "sprint_004",
    "sprint_005",
    "sprint_006",
    "sprint_007",
    "sprint_008",
    "sprint_009",
    "sprint_010",
    "sprint_011",
    "sprint_012",
    "sprint_013",
]

# Your new names (WITHOUT extensions)
new_names = [
    "sprite_002",
    "sprite_003",
    "sprite_004",
    "sprite_005",
    "sprite_006",
    "sprite_007",
    "sprite_008",
    "sprite_009",
    "sprite_010",
    "sprite_011",
    "sprite_012",
    "sprite_013",
]

# Allowed image extensions
valid_exts = [".png", ".jpg", ".jpeg", ".gif", ".webp"]

if len(old_names) != len(new_names):
    print("ERROR: old_names and new_names must have the same length.")
    exit()

for old, new in zip(old_names, new_names):
    # Find the file with the old name (any extension)
    found = False
    for ext in valid_exts:
        old_path = os.path.join(folder, old + ext)
        if os.path.exists(old_path):
            new_path = os.path.join(folder, new + ext)
            os.rename(old_path, new_path)
            print(f"Renamed: {old}{ext} → {new}{ext}")
            found = True
            break

    if not found:
        print(f"WARNING: Could not find file for '{old}'")
