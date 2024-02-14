import os

def update_readme(root, week_folder, week):
    readme_path = os.path.join(root, 'README.md')

    with open(readme_path, 'a') as readme_file:
        readme_file.write(f"\n## {os.path.basename(week_folder)}\n")

        for problem_folder in sorted(os.listdir(week_folder)):
            if os.path.isdir(os.path.join(week_folder, problem_folder)):
                problem_readme = os.path.join(week_folder, problem_folder, 'README.md')
                if os.path.exists(problem_readme):
                    readme_file.write(f"- [{problem_folder}]({os.path.join(week, problem_folder)}/README.md)\n")

def main():
    base_folder = os.getcwd()
    root = os.path.join(base_folder, "heesu")
    week_input = input("Enter the week number (e.g., Week6): ")
    week_folder = os.path.join(root, week_input)
    print(week_input)

    update_readme(root, week_folder, week_input)

if __name__ == "__main__":
    main()